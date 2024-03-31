package com.giftbox.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="GiftBoxColors")
@Data@AllArgsConstructor
@NoArgsConstructor
public class GiftBoxColor {

    @Id
    private String BoxColorId;
    private String color;
    private String image;


}
