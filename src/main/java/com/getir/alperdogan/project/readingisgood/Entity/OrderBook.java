package com.getir.alperdogan.project.readingisgood.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OrderBook")
@Data
public class OrderBook {

    private Integer quantity;
    private Integer bookId;

    public OrderBook() {
    }

    public OrderBook(Integer quantity, Integer bookId) {
        this.quantity = quantity;
        this.bookId = bookId;
    }
}
