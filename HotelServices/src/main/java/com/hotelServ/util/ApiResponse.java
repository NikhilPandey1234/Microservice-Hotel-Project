package com.hotelServ.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Data
public class ApiResponse {

    private String message;

    private boolean success;

    private LocalDate date;

    private LocalTime time;

    public ApiResponse(String message, boolean success, LocalDate date, LocalTime time) {
        this.message = message;
        this.success = success;
        this.date = date;
        this.time = time;
    }
}
