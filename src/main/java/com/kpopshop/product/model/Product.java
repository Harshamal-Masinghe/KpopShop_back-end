package com.kpopshop.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="Product")
@Data // Lombok annotation for generating getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor // Lombok annotation to generate constructor with all arguments
@NoArgsConstructor // Lombok annotation to generate constructor with no arguments
public class Product {

    @Id
    private String id;
    private String productId;
    private Category category;
    private String name;
    private Size size;
    private String imageUrl;
    private String description;
    private boolean giftBoxProduct;
    private double price;
    private int quantity;

}