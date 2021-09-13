package com.getir.alperdogan.project.readingisgood.Service;

import com.getir.alperdogan.project.readingisgood.DAO.CustomerDAO;
import com.getir.alperdogan.project.readingisgood.Entity.Customer;
import com.getir.alperdogan.project.readingisgood.Exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public Customer registerCustomer(Customer customer)
    {
        return customerDAO.insertCustomer(customer);
    }

    public Customer findCustomer(Integer customerId) throws CustomerNotFoundException {
        return customerDAO.findCustomer(customerId).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findCustomerByEmail(String email)
    {
        return customerDAO.findCustomerByEmail(email);
    }
}
