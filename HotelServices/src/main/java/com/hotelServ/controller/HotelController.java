package com.hotelServ.controller;

import com.hotelServ.dto.HotelDTOs;
import com.hotelServ.services.HotelService;
import com.hotelServ.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDTOs> createHotel(@RequestBody HotelDTOs hotelDTOs){
        HotelDTOs hotelDTO =  hotelService.createHotel(hotelDTOs);
        return  new ResponseEntity<>(hotelDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDTOs> updateHotel(@PathVariable String hotelId, @RequestBody HotelDTOs hotelDTOs){
        HotelDTOs hotelDTO = hotelService.updateHotel(hotelId, hotelDTOs);
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTOs>> getAllHotels(){
        List<HotelDTOs> hotelDTOs = hotelService.getAllHotels();
        return new ResponseEntity<>(hotelDTOs, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public  ResponseEntity<HotelDTOs> getHotelById(@PathVariable String hotelId){
        HotelDTOs hotelDTOs = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(hotelDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelId){
        ApiResponse response = hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
