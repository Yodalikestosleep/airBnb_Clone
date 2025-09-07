package com.example.yoda.airBnbApp.service;

import com.example.yoda.airBnbApp.dto.HotelDto;
import com.example.yoda.airBnbApp.entity.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
}
