package com.kpopshop.cart.model;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String userId; // If your application has user authentication
    private List<CartItem> items = new ArrayList<>();

}
