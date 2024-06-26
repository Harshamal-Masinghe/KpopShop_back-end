package com.kpopshop.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    private String addressId;
    private String street;
    private String city;
    private String zipCode;
    // Add any other fields as needed
}
