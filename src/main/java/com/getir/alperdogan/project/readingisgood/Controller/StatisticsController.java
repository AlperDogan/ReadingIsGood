package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.Statistic;
import com.getir.alperdogan.project.readingisgood.Service.OrderService;
import com.getir.alperdogan.project.readingisgood.Service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/{customerId}")
    public List<Statistic> getStatisticsByCustomerId(@PathVariable("customerId") Integer customerId)
    {
        return statisticService.findStatisticsByCustomerId(customerId);
    }

}
