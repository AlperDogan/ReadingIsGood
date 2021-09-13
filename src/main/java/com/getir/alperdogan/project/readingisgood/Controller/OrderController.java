package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.*;
import com.getir.alperdogan.project.readingisgood.Exception.*;
import com.getir.alperdogan.project.readingisgood.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;


    @PostMapping(value = "/newOrder/{customerId}")
    public Order newOrder(@PathVariable("customerId") Integer customerId, @RequestBody OrderBook orderBook) throws NotEnoughBookStockException, BookNotFoundException, OrderNotFoundException, PaymentException, CustomerNotFoundException {
        Book book = bookService.findBook(orderBook.getBookId()).orElseThrow(BookNotFoundException::new);
        int orderedBookQuantity = orderBook.getQuantity();
        int updatedStockNumber = book.getStockNumber() - orderedBookQuantity;
        if (!(updatedStockNumber < 0)) {
            book.setStockNumber(updatedStockNumber);
            bookService.updateBook(book);
            Order order = prepareOrder(customerId, orderBook);
            orderService.insertOrder(order);
            order = makePayment(customerId, order.getOrderId());
            order.setStatus(Status.COMPLETED);
            orderService.updateOrder(order);
            insertStatistic(order);
            return order;
        } else {
            throw new NotEnoughBookStockException();
        }
    }

    private void insertStatistic(Order order) {
        int mount = order.getOrderDate().getMonth();
        Statistic statistic = statisticService.findStatisticByCustomerIdAndMount(order.getCustomerId(), mount);
        if (statistic != null) {
            int statisticOrderCount = statistic.getOrderCount();
            Double statisticPurchasedAmount = statistic.getPurchasedAmount();
            int statisticBookCount = statistic.getBookCount();
            statistic.setOrderCount(statisticOrderCount + 1);
            statistic.setPurchasedAmount(statisticPurchasedAmount + order.getTotalPrice());
            statistic.setBookCount(statisticBookCount + order.getOrderBook().getQuantity());
            statisticService.updateStatistic(statistic);
        } else {
            Statistic statistic1 = new Statistic();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(order.getOrderDate());
            statistic1.setStatisticId(sequenceGenerator.generateSequence(Statistic.SEQUENCE_NAME));
            statistic1.setCustomerId(order.getCustomerId());
            statistic1.setOrderCount(1);
            statistic1.setBookCount(order.getOrderBook().getQuantity());
            statistic1.setMonth(calendar.get(Calendar.MONTH));
            statistic1.setPurchasedAmount(order.getTotalPrice());
            statisticService.insertStatistic(statistic1);
        }


    }

    private Order makePayment(Integer customerId, Integer orderId) throws CustomerNotFoundException, OrderNotFoundException, PaymentException {
        Customer customer = customerService.findCustomer(customerId);
        Order order = orderService.findOrder(orderId);
        order.setStatus(Status.AWATING_PAYMENT);
        orderService.updateOrder(order);
        if (customer.getBalance() >= order.getTotalPrice()) {
            order.setStatus(Status.PAYMENT_RECEIVED);
            orderService.updateOrder(order);
            return order;
        } else {
            throw new PaymentException();
        }
    }

    @GetMapping("/{orderId}")
    public Order findOrderById(@PathVariable("orderId") Integer orderId) throws OrderNotFoundException {
        return orderService.findOrder(orderId);
    }

    @GetMapping(value = "/findOrdersDateRange/{startDate}/{endDate}")
    public List<Order> findOrdersByDateRange(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return orderService.findOrdersByDateRange(startDate, endDate);
    }

    private Order prepareOrder(Integer customerId, OrderBook orderBook) {
        Order order = new Order();
        Optional<Book> book = bookService.findBook(orderBook.getBookId());
        Double orderPrice = book.get().getPrice() * orderBook.getQuantity();
        order.setOrderId(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));
        order.setCustomerId(customerId);
        order.setOrderDate(new Date());
        order.setStatus(Status.PENDING);
        order.setOrderBook(orderBook);
        order.setTotalPrice(orderPrice);
        return order;
    }
}
