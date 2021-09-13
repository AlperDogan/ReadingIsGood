package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Order;
import com.getir.alperdogan.project.readingisgood.Entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticDAO {

    @Autowired
    StatisticRepository statisticRepository;

    public Statistic insertStatistic(Statistic statistic)
    {
        return statisticRepository.insert(statistic);
    }

    public Statistic updateStatistic(Statistic statistic){
        return statisticRepository.save(statistic);
    }

    public List<Statistic> findStatisticsByCustomerId(Integer customerId)
    {
        return statisticRepository.findAllByCustomerId((customerId));
    }

    public Statistic findStatisticCustomerIdAndMonth(Integer customerId,Integer month)
    {
        return statisticRepository.findByCustomerIdAndMonth(customerId,month);
    }
}

