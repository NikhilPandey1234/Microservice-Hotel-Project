package com.hotelServ.services;

import com.hotelServ.dto.HotelDTOs;
import com.hotelServ.util.ApiResponse;

import java.util.List;

public interface HotelService {

    HotelDTOs createHotel(HotelDTOs hotelDTOs);

    HotelDTOs  updateHotel(String hotelId, HotelDTOs hotelDTOs);

    HotelDTOs getHotelById(String hotelId);

    List<HotelDTOs> getAllHotels();

    ApiResponse deleteHotel(String hotelId);

}
