package com.kpopshop.payment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import java.util.Date;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment")


public class PaymentModel {

    @Id
    private String id;
    private String paymentId;
    private String name;
    private String paymentType;
    private Date paymentDate;
    private Date updateDate;
    private Boolean completed;

}
