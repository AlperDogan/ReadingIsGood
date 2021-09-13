package com.getir.alperdogan.project.readingisgood.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Statistic")
@Data
public class Statistic {

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private Integer statisticId;
    private Integer customerId;
    private Integer month;
    private Integer orderCount;
    private Integer bookCount;
    private Double purchasedAmount;

    public Statistic() {
    }

    public Statistic(Integer customerId, Integer month, Integer orderCount, Integer bookCount, Double purchasedAmount) {
        this.customerId = customerId;
        this.month = month;
        this.orderCount = orderCount;
        this.bookCount = bookCount;
        this.purchasedAmount = purchasedAmount;
    }
}
