package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String status;
    private String imageURL;
    private String size;
    private boolean giftBoxProduct;

    @DBRef
    private Category category;

    private List<ReorderLevel> reorderLevels;
    private List<Order> orders;
    private List<Alert> alerts;

    public void setProductId(String productId) {
        this.productID = productId;
    }
}
