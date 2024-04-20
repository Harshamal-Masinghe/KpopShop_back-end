package com.kpopshop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Complain")
public class ComplainModel {
    @Id
    private String complainId;
    private String complainDetails;
    private String complainStatus;
    private String complainType;
    private String customerMail;
    private String  images;


}
