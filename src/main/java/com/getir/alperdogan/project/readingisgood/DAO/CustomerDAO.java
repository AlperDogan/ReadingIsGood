package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer insertCustomer(Customer customer)
    {
        return customerRepository.insert(customer);
    }

    public Optional<Customer> findCustomer(Integer customerId)
    {
        return customerRepository.findById(customerId);
    }

    public Iterable<Customer> findCustomers(Iterable<Integer> customerIdentityNumbers)
    {
     return customerRepository.findAllById(customerIdentityNumbers);
    }

    public Customer findCustomerByEmail(String email)
    {
        return customerRepository.findByEmail(email);
    }
}
