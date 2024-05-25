package com.userSer.external;

import com.userSer.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICES")
public interface HotelService {

    @GetMapping("/api/hotel/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
