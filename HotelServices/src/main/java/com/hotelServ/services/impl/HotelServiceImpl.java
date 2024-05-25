package com.hotelServ.services.impl;

import com.hotelServ.dto.HotelDTOs;
import com.hotelServ.exceptions.ResourceNotFoundException;
import com.hotelServ.modal.HotelEntity;
import com.hotelServ.repsoitory.HotelRepository;
import com.hotelServ.services.HotelService;
import com.hotelServ.util.ApiResponse;
import com.hotelServ.util.ModelMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMappers modelMappers;

    @Override
    public HotelDTOs createHotel(HotelDTOs hotelDTOs) {
        HotelEntity hotel = modelMappers.HotelDtoToHotelEntity(hotelDTOs);
        String randomUserId = UUID.randomUUID().toString();
        hotel.setHotelId(randomUserId);
        HotelEntity savedHotel = hotelRepository.save(hotel);
        return modelMappers.HotelEntityToHotelDto(savedHotel);
    }

    @Override
    public HotelDTOs updateHotel(String hotelId, HotelDTOs hotelDTOs) {
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this id :"+hotelId));
        hotel.setHotelName(hotelDTOs.getHotelName());
        hotel.setLocation(hotelDTOs.getLocation());
        hotel.setDescription(hotelDTOs.getDescription());
        HotelEntity updatedHotel = hotelRepository.save(hotel);
        return modelMappers.HotelEntityToHotelDto(updatedHotel);
    }

    @Override
    public HotelDTOs getHotelById(String hotelId) {
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this id :"+hotelId));
        return modelMappers.HotelEntityToHotelDto(hotel);
    }

    @Override
    public List<HotelDTOs> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelEntity -> modelMappers.HotelEntityToHotelDto(hotelEntity)).collect(Collectors.toList());
    }

    @Override
    public ApiResponse deleteHotel(String hotelId) {
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found With this id :"+hotelId));
        LocalDateTime dateTime = LocalDateTime.now();
        hotelRepository.delete(hotel);
        return new ApiResponse("Hotel Deleted Successfully", true, dateTime.toLocalDate(), dateTime.toLocalTime());

    }
}
