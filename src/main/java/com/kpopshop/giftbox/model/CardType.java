package com.kpopshop.giftbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CardTypes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardType {
    @Id
    private String cardId;
    private String type;
    private String image;
}
