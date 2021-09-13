package com.getir.alperdogan.project.readingisgood.Service;

import com.getir.alperdogan.project.readingisgood.DAO.OrderDAO;
import com.getir.alperdogan.project.readingisgood.Entity.Order;
import com.getir.alperdogan.project.readingisgood.Exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public Order insertOrder(Order order) {
        return orderDAO.insertOrder(order);
    }

    public Order findOrder(Integer orderId) throws OrderNotFoundException {
        return orderDAO.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    public List<Order> findByCustomerId(Integer customerId) {
        return orderDAO.findOrdersByCustomerId(customerId);
    }

    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {
        return orderDAO.findOrdersByDateRange(startDate, endDate);
    }

}
