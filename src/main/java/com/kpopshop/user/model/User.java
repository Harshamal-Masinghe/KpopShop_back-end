package com.kpopshop.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userID;
    private String userName;
    private String addressID;
    private String address;
    private String password;
    private String email;
    private String street;
    private String city;
    private String zipCode;




}

