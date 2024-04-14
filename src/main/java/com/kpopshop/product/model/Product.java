package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId;
    private String category;
    private String name;
    private String size;
    private String imageUrl;
    private String description;
    private boolean giftBoxProduct;
    private double price;
    private int quantity;
    //private Category category;  Representing the foreign key relationship



}

