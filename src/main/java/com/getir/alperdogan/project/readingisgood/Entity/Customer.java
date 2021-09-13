package com.getir.alperdogan.project.readingisgood.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
@Data
public class Customer {

    @Id
    private Integer identityNumber;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Address address;
    private Double balance;

    public Customer(Integer identityNumber, String name, String surname, String email, String phoneNumber, Address address, Double balance) {
        this.identityNumber = identityNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = balance;
    }
}
