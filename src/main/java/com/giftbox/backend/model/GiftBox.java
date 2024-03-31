package com.giftbox.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="GiftBox")
@Data@AllArgsConstructor
@NoArgsConstructor
public class GiftBox {
        @Id
        private String giftBoxId;
        private List<GiftBoxColor> boxColor;
        private List<CardType> cardType;
        private String message;
        private List<GiftBoxProduct> products;
        private double totalAmount;



        public static class GiftBoxProduct {
                @Getter
                private String productId;
                private String size;
                @Getter@Setter
                private int quantity;

        }
}
