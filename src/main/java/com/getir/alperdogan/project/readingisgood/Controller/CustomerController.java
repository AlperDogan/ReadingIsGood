package com.getir.alperdogan.project.readingisgood.Controller;

import com.getir.alperdogan.project.readingisgood.Entity.Customer;
import com.getir.alperdogan.project.readingisgood.Entity.Order;
import com.getir.alperdogan.project.readingisgood.Exception.CustomerNotFoundException;
import com.getir.alperdogan.project.readingisgood.Exception.NotUniqueValueException;
import com.getir.alperdogan.project.readingisgood.Service.CustomerService;
import com.getir.alperdogan.project.readingisgood.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Customer registerCustomer(@RequestBody Customer customer) throws NotUniqueValueException {
        if (customerService.findCustomerByEmail(customer.getEmail()) == null) {
            return customerService.registerCustomer(customer);
        } else {
            throw new NotUniqueValueException();
        }
    }

    @GetMapping(value = "/getCustomerById/{identityNumber}")
    public Customer findCustomer(@PathVariable("identityNumber") Integer customerId) throws CustomerNotFoundException {
        return customerService.findCustomer(customerId);
    }

    @GetMapping(value = "/getCustomerOrder/{identityNumber}")
    public List<Order> getCustomerOrder(@PathVariable("identityNumber") Integer customerId) {
        return orderService.findByCustomerId(customerId);
    }

}
