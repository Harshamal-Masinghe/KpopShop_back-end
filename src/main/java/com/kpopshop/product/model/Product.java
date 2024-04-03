package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
<<<<<<< HEAD
import org.springframework.data.mongodb.core.mapping.DBRef;
=======
>>>>>>> 47a4117566cbd92523a056bf93a5d4fffd5353c2
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

<<<<<<< HEAD

=======
@Document(collection="Product")
>>>>>>> 47a4117566cbd92523a056bf93a5d4fffd5353c2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productID;
    private String name;
    private String description;
    private double price;
<<<<<<< HEAD
    private int quantity;
    private boolean giftBoxProduct;

    @DBRef
    private Category category;

    private List<ReorderLevel> reorderLevels;
    private List<Order> orders;
    private List<Alert> alerts;

    public void setProductId(String productId) {
        this.productID = productId;
    }
=======
    private String status;
    private String image;
    //private Category category;  Representing the foreign key relationship
    //private List<Size> sizes; create sizes class including quantity
    private boolean giftBoxProduct;


>>>>>>> 47a4117566cbd92523a056bf93a5d4fffd5353c2
}
