package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Integer> {

    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

    List<Order> findAllByCustomerId(Integer customerId);
}
