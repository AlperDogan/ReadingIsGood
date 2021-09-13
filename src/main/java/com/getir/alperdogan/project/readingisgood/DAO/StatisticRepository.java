package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StatisticRepository extends MongoRepository<Statistic, Integer> {

    List<Statistic> findAllByCustomerId(Integer customerId);

    Statistic findByCustomerIdAndMonth(Integer customerId,Integer month);

}
