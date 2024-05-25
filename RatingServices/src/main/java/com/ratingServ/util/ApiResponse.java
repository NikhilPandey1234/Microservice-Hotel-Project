package com.ratingServ.util;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
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
