package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.*;
import com.getir.alperdogan.project.readingisgood.Exception.*;
import com.getir.alperdogan.project.readingisgood.Service.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController mockOrderController;

    @Mock
    private OrderService mockOrderService;

    @Mock
    private BookService mockBookService;

    @Mock
    private CustomerService mockCustomerService;

    @Mock
    private StatisticService mockStatisticService;

    @Mock
    private SequenceGeneratorService mockSequenceGenerator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void newOrder() throws NotEnoughBookStockException, OrderNotFoundException, PaymentException, CustomerNotFoundException, BookNotFoundException {
        Book book = new Book(
                1,
                "Intibah",
                "Namık Kemal",
                "Roman",
                22.0,
                5);

        Customer mockCustomer = new Customer(9999,
                "new",
                "customer",
                "a@gmail.com",
                "05555555555",
                new Address("Turkey", "izmir", "Cesme", "yol", 1, 2, 35475),
                10000.0);

        Order mockOrder = new Order(1,
                9999,
                new Date(),
                50.0,
                new OrderBook(1, 1), Status.PENDING);

        Statistic mockStatistic = new Statistic(9999,
                8,
                1,
                1,
                22.0);

        when(mockBookService.findBook(1)).thenReturn(java.util.Optional.of(book));
        when(mockSequenceGenerator.generateSequence(ArgumentMatchers.anyString())).thenReturn(1);
        when(mockBookService.updateBook(book)).thenReturn(book);
        when(mockOrderService.insertOrder(ArgumentMatchers.any(Order.class))).thenReturn(mockOrder);
        when(mockCustomerService.findCustomer(9999)).thenReturn(mockCustomer);
        when(mockOrderService.findOrder(1)).thenReturn(mockOrder);
        when(mockOrderService.updateOrder(mockOrder)).thenReturn(mockOrder);
        when(mockStatisticService.insertStatistic(ArgumentMatchers.any(Statistic.class))).thenReturn(mockStatistic);

        Order insertedOrder = mockOrderController.newOrder(9999, new OrderBook(1, 1));

        Assertions.assertThat(insertedOrder.getOrderId()).isEqualTo(1);

    }

    @Test
    public void findOrderById() throws OrderNotFoundException {
        Order mockOrder = new Order(1234,
                9999,
                new Date(),
                50.0,
                new OrderBook(1, 1), Status.PENDING);
        when(mockOrderService.findOrder(1)).thenReturn(mockOrder);

        Order foundOrder = mockOrderController.findOrderById(1);

        Assertions.assertThat(foundOrder.getOrderId()).isEqualTo(1234);

    }


}