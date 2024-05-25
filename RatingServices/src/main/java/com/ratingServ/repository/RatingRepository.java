package com.ratingServ.repository;

import com.ratingServ.modal.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<RatingEntity, String > {

    List<RatingEntity> getRatingsByUserId(String userId);

    List<RatingEntity> getRatingsByHotelId(String hotelId);

    List<RatingEntity> findByUserIdAndHotelId(String userId, String hotelId);

}
