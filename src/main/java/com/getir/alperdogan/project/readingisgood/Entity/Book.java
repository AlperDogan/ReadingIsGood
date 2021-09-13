package com.getir.alperdogan.project.readingisgood.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Book")
@Data
public class Book {

    @Id
    private Integer bookId;
    private String name;
    private String author;
    private String category;
    private Double price;
    private Integer stockNumber;

    public Book() {
    }

    public Book(Integer bookId, String name, String author, String category, Double price, Integer stockNumber) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.stockNumber = stockNumber;
    }
}

