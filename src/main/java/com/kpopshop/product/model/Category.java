package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private String categoryId;
    private String name;
    private int quantity; // Quantity associated with the category
}
