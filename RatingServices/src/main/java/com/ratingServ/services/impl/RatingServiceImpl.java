package com.ratingServ.services.impl;

import com.ratingServ.dto.RatingDTO;
import com.ratingServ.exceptions.ResourceNotFoundException;
import com.ratingServ.modal.RatingEntity;
import com.ratingServ.repository.RatingRepository;
import com.ratingServ.services.RatingService;
import com.ratingServ.util.ModelMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ModelMappers modelMappers;

    @Override
    public RatingDTO create(RatingDTO ratingDTO) {
        RatingEntity rating = modelMappers.ratingDtoToRatingEntity(ratingDTO);
        String randomUserId = UUID.randomUUID().toString();
        rating.setRatingId(randomUserId);
        RatingEntity savedRating = ratingRepository.save(rating);
        return modelMappers.ratingEntityToRatingDto(savedRating);
    }

    @Override
    public RatingDTO update(String ratingId, RatingDTO ratingDTO) {
        RatingEntity existingRating = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating not found with tis Id :"+ratingId));
        existingRating.setRating(ratingDTO.getRating());
        existingRating.setFeedback(ratingDTO.getFeedback());
        RatingEntity updatedRating = ratingRepository.save(existingRating);
        return modelMappers.ratingEntityToRatingDto(updatedRating);
    }

   /* @Override
    public RatingDTO updateHotelRatingByUserId(String userId, String hotelId, RatingDTO ratingDTO) {
        RatingEntity existingRating = ratingRepository.findByUserIdAndHotelId(userId, hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for user: " + userId + " and hotel: " + hotelId));

        // Update the existing rating with the new values
        existingRating.setRating(ratingDTO.getRating());
        existingRating.setComment(ratingDTO.getComment());

        // Save the updated rating entity
        RatingEntity updatedRating = ratingRepository.save(existingRating);

        // Convert and return the updated rating entity as DTO
        return modelMappers.ratingEntityToRatingDto(updatedRating);
    }*/

    @Override
    public RatingDTO getRatingById(String id) {
        RatingEntity rating = ratingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rating not found with this id :"+id));
        return modelMappers.ratingEntityToRatingDto(rating);
    }

    @Override
    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll().stream().map(ratingEntity -> modelMappers.ratingEntityToRatingDto(ratingEntity)).collect(Collectors.toList());
    }

    @Override
    public List<RatingEntity> getRatingByUserId(String userId) {
        return ratingRepository.getRatingsByUserId(userId);
    }

    @Override
    public List<RatingEntity> getRatingByHotelId(String hotelId) {
        return ratingRepository.getRatingsByHotelId(hotelId);

    }
}
