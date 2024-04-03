package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ReorderLevel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReorderLevel {
    @Id
    private String id;

    @DBRef
    private Product product;

    private int reorderThreshold;
}
