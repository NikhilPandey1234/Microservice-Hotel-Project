package com.ratingServ.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private String ratingId;

    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;
}
