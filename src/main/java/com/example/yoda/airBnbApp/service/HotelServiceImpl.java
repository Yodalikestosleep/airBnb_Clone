package com.example.yoda.airBnbApp.service;

import com.example.yoda.airBnbApp.dto.HotelDto;
import com.example.yoda.airBnbApp.entity.Hotel;
import com.example.yoda.airBnbApp.exception.ResourceNotFoundException;
import com.example.yoda.airBnbApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //This will create a constructor for me with required arguments
@Slf4j //This annotation will create a default logger for me
public class HotelServiceImpl implements HotelService {


    private final HotelRepository hotelRepository;  //I didnt had to create a constructor because of the RequiredArgsConstructor annotation
    private final ModelMapper modelMapper;



    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating new hotel with name : {}" + hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created new hotel with id : {}" + hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);



    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel with name : {}" + id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : " +id));
        return modelMapper.map(hotel,HotelDto.class);

    }
}
