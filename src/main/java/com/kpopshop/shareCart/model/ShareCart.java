package com.kpopshop.shareCart.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "ShareCart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareCart {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String accessType;

    // Getters and setters
}
