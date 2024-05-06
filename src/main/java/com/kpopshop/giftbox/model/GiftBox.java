package com.kpopshop.giftbox.model;

import com.kpopshop.product.model.Product;
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
        private GiftBoxColor boxColor;
        private CardType cardType;
        private String message = "";
        private List<GiftBoxProduct> products;
        private double totalAmount;





        @Setter
        @Getter
        public static class GiftBoxProduct {
                Product product;
                private String productId;
                private String name;
                private double price;
                private int quantity;

        }
}
