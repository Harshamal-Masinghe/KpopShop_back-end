package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productID;
    private String name;
    private String description;
    private double price;
    private String status;
    private String image;
    //private Category category;  Representing the foreign key relationship
    //private List<Size> sizes; create sizes class including quantity
    private boolean giftBoxProduct;


}
