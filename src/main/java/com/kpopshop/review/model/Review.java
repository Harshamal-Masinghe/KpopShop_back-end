package com.kpopshop.review.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection="Review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String reviewId;
    private String name;
    private String email;
    private String comment;
    private List<String> images;
    private int rating;

}
