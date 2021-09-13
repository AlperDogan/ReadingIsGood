package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.Statistic;
import com.getir.alperdogan.project.readingisgood.Service.StatisticService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticsControllerTest {

    @InjectMocks
    private StatisticsController mockStatisticsController;

    @Mock
    private StatisticService mockStatisticService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStatisticsByCustomerId() {
        Statistic mockStatistic = new Statistic(9999,
                8,
                1,
                1,
                22.0);
        List<Statistic> mockListStatistics= new ArrayList<>();
        mockListStatistics.add(mockStatistic);

        when(mockStatisticService.findStatisticsByCustomerId(9999)).thenReturn(mockListStatistics);

        List<Statistic> foundMockStatistic = mockStatisticsController.getStatisticsByCustomerId(9999);

        Assertions.assertThat(foundMockStatistic.get(0).getMonth()).isEqualTo(8);
    }
}