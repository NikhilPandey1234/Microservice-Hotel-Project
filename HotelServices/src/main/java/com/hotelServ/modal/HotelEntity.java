package com.hotelServ.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity {

    @Id
    private String hotelId;

    @Column(name = "hotel_name")
    private String hotelName;;

    @Column(name = "hotel_location")
    private String location;

    @Column(name = "hotel_description")
    private String description;
}
