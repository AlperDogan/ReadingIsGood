package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDAO {

    @Autowired
    private OrderRepository orderRepository;

    public Order insertOrder(Order order) {
        return orderRepository.insert(order);
    }

    public Order updateOrder(Order order) { return orderRepository.save(order);}

    public Optional<Order> findOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    public List<Order> findOrdersByCustomerId(Integer customerId)
    {
        return orderRepository.findAllByCustomerId((customerId));
    }
}
