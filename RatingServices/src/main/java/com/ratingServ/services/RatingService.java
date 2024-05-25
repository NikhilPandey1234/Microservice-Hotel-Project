package com.ratingServ.services;

import com.ratingServ.dto.RatingDTO;
import com.ratingServ.modal.RatingEntity;

import java.util.List;

public interface RatingService {

    RatingDTO create(RatingDTO ratingDTO);

    RatingDTO update(String ratingId, RatingDTO ratingDTO);

/*
    RatingDTO updateHotelRatingByUserId(String userId, String hotelId, RatingDTO ratingDTO);
*/

    RatingDTO getRatingById(String id);

    List<RatingDTO> getAllRatings();

    List<RatingEntity> getRatingByUserId(String userId);

    List<RatingEntity> getRatingByHotelId(String hotelId);

}
