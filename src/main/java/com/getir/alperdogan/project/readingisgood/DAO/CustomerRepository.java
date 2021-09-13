package com.getir.alperdogan.project.readingisgood.DAO;

import com.getir.alperdogan.project.readingisgood.Entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

    Customer findByEmail(String email);
}
