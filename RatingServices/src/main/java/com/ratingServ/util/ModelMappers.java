package com.ratingServ.util;

import com.ratingServ.dto.RatingDTO;
import com.ratingServ.modal.RatingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMappers {

    @Autowired
    private ModelMapper modelMapper;

    public RatingEntity ratingDtoToRatingEntity(RatingDTO ratingDTO){
        RatingEntity ratingEntity = modelMapper.map(ratingDTO, RatingEntity.class);
        return ratingEntity;
    }

    public RatingDTO ratingEntityToRatingDto(RatingEntity ratingEntity){
        RatingDTO ratingDTO = modelMapper.map(ratingEntity, RatingDTO.class);
        return ratingDTO;
    }
}
