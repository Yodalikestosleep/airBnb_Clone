package com.example.yoda.airBnbApp.controller;


import com.example.yoda.airBnbApp.dto.HotelDto;
import com.example.yoda.airBnbApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService; //we use hotel service here instead of hotel reposittory
    //this way we follow the mvc architecture

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto){

        HotelDto hotel = hotelService.createNewHotel(hotelDto);
        log.info("User send a request to create a new hotel with hotel id : {}" + hotel.getId());
        return new ResponseEntity<>(hotel,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<HotelDto> getHotelById(@RequestParam(name = "id") Long id){
        HotelDto hoteldto = hotelService.getHotelById(id);
        return new ResponseEntity<>(hoteldto,HttpStatus.OK);
    }


}
