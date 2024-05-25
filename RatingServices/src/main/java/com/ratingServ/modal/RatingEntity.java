package com.ratingServ.modal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("users_hotel_ratings")
public class RatingEntity {

    @Id
    private String ratingId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "hotelId")
    private String hotelId;

    @Column(name = "hotel_ratings")
    private int rating;

    @Column(name = "hotel_feedback")
    private String feedback;
}
