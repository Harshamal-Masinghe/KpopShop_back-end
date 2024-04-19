package com.kpopshop.order.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Order")
public class OrderModel {

    @Id
    private String orderId;
    private String customerId;
    private String productId;
    private String address;
    private LocalDate orderDate;
    private int productQty;
}
