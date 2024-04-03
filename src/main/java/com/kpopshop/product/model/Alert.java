package com.kpopshop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alerts")
public class Alert {
    @Id
    private String id;

    @DBRef
    private Product product;

    private String message;
    private String alertType;
    private Date alertDate;
    private boolean acknowledged;
    private boolean dismissed;
}
