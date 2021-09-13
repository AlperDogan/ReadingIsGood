package com.getir.alperdogan.project.readingisgood.Service;

import com.getir.alperdogan.project.readingisgood.DAO.StatisticDAO;
import com.getir.alperdogan.project.readingisgood.Entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private StatisticDAO statisticDAO;

    public Statistic insertStatistic(Statistic statistic) {
        return statisticDAO.insertStatistic(statistic);
    }

    public Statistic updateStatistic(Statistic statistic) {
        return statisticDAO.updateStatistic(statistic);
    }

    public List<Statistic> findStatisticsByCustomerId(Integer customerId) {
        return statisticDAO.findStatisticsByCustomerId(customerId);
    }

    public Statistic findStatisticByCustomerIdAndMount(Integer customerId, Integer mount) {
        return statisticDAO.findStatisticCustomerIdAndMonth(customerId, mount);
    }
}
