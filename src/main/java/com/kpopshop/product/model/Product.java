package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Annotation to specify MongoDB collection
@Document(collection="Product")
@Data // Lombok annotation for generating getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor // Lombok annotation to generate constructor with all arguments
@NoArgsConstructor // Lombok annotation to generate constructor with no arguments
public class Product {

    // MongoDB document ID
    @Id
    private String productId;

    // Name of the product
    private String name;

    // URL of the product image
    private String imageUrl;

    // Category of the product (represented by Category class)
    private Category categoryId;

    // Description of the product
    private String description;

    // Size of the product (represented by Size class)
    private Size sizeId;

    // Quantity of the product available
    private int quantity;

    // Price per unit of the product
    private double pricePerUnit;

    // Indicates whether the product is a gift box product or not
    private boolean giftBoxProduct;
}
