package com.getir.alperdogan.project.readingisgood.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
@Data
public class Address {

    private String country;
    private String city;
    private String distinct;
    private String street;
    private Integer buildingNumber;
    private Integer doorNumber;
    private Integer postalCode;

    public Address(String country, String city, String distinct, String street, Integer buildingNumber, Integer doorNumber, Integer postalCode) {
        this.country = country;
        this.city = city;
        this.distinct = distinct;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
    }
}
