package com.getir.alperdogan.project.readingisgood.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Order")
@Data
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private Integer orderId;
    private Integer customerId;
    private Date orderDate;
    private Double totalPrice;
    private OrderBook orderBook;
    private Status status;

    public Order() {
    }

    public Order(Integer orderId, Integer customerId, Date orderDate, Double totalPrice, OrderBook orderBook, Status status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderBook = orderBook;
        this.status = status;
    }
}
