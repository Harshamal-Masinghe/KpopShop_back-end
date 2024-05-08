package com.kpopshop.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveCardModel {

    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

}
