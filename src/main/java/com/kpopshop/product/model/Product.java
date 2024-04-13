package com.kpopshop.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// Annotation to specify MongoDB collection
@Document(collection="Products")
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

    // Date and time of the low inventory alert trigger
    @Getter
    @Setter
    private LocalDateTime alertTriggerDateTime;

}
