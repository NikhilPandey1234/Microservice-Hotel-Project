package com.hotelServ.util;

import com.hotelServ.dto.HotelDTOs;
import com.hotelServ.modal.HotelEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMappers {

    @Autowired
    private ModelMapper modelMapper;

    public HotelEntity HotelDtoToHotelEntity(HotelDTOs hotelDTOs){
        HotelEntity hotelEntity = modelMapper.map(hotelDTOs, HotelEntity.class);
        return hotelEntity;
    }

    public HotelDTOs HotelEntityToHotelDto(HotelEntity hotelEntity){
        HotelDTOs hotelDTOs = modelMapper.map(hotelEntity, HotelDTOs.class);
        return hotelDTOs;
    }
}
