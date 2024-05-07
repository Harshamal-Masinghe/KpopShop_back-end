package com.kpopshop.cart.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Cart")
@Data
public class CartItem {

    @Id
    private String id;
    private String productId;
    private String name;
    private double price;
    private int quantity;

}
