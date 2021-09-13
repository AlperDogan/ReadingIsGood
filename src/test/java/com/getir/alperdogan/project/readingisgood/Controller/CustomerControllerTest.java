package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.*;
import com.getir.alperdogan.project.readingisgood.Exception.CustomerNotFoundException;
import com.getir.alperdogan.project.readingisgood.Exception.NotUniqueValueException;
import com.getir.alperdogan.project.readingisgood.Service.CustomerService;
import com.getir.alperdogan.project.readingisgood.Service.OrderService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController mockCustomerController;

    @Mock
    private CustomerService mockCustomerService;

    @Mock
    private OrderService mockOrderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerCustomer() throws CustomerNotFoundException, NotUniqueValueException {
        Customer nonExistingCustomer = new Customer(9999,
                "new",
                "customer",
                "a@gmail.com",
                "05555555555",
                new Address("Turkey","izmir","Cesme","yol",1,2,35475),
                10000.0);

        when(mockCustomerService.registerCustomer(ArgumentMatchers.any(Customer.class))).thenReturn(nonExistingCustomer);

        Customer found = mockCustomerService.registerCustomer(nonExistingCustomer);
        Assertions.assertThat(found.getIdentityNumber()).isEqualTo(9999);
    }

    @Test
    public void findCustomer() throws CustomerNotFoundException {
        Customer nonExistingCustomer = new Customer(9999,
                "new",
                "customer",
                "a@gmail.com",
                "05555555555",
                new Address("Turkey","izmir","Cesme","yol",1,2,35475),
                10000.0);

        when(mockCustomerService.findCustomer(ArgumentMatchers.anyInt())).thenReturn(nonExistingCustomer);

        Customer found = mockCustomerController.findCustomer(9999);
        Assertions.assertThat(found.getIdentityNumber()).isEqualTo(9999);
    }

    @Test
    public void getCustomerOrder() {
        List<Order> mockOrderList = new ArrayList<>();
        Order mockOrder = new Order();
        mockOrder.setOrderId(1);
        mockOrder.setCustomerId(9999);
        mockOrder.setOrderDate(new Date());
        mockOrder.setOrderBook(new OrderBook(1,1));
        mockOrder.setTotalPrice(100.0);
        mockOrder.setStatus(Status.COMPLETED);
        mockOrderList.add(mockOrder);

        when(mockOrderService.findByCustomerId(9999)).thenReturn(mockOrderList);

        List<Order> foundMockOrderList = mockCustomerController.getCustomerOrder(9999);

        Assertions.assertThat(foundMockOrderList.get(0).getOrderId()).isEqualTo(1);

    }
}