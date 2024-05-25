package com.ratingServ.controller;

import com.ratingServ.dto.RatingDTO;
import com.ratingServ.modal.RatingEntity;
import com.ratingServ.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDTO> create(@RequestBody RatingDTO ratingDTO){
        RatingDTO rating = ratingService.create(ratingDTO);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings(){
        List<RatingDTO> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingEntity>> getRatingByUserId(@PathVariable String userId){
        List<RatingEntity> ratings = ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getRatingByHotelId(@PathVariable String hotelId){
        List<RatingEntity> ratings = ratingService.getRatingByUserId(hotelId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}

