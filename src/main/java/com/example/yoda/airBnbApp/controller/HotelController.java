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

    @PutMapping
    public ResponseEntity<HotelDto> updateHotelById(@RequestParam(required = true) Long id, @RequestBody HotelDto hotelDto){
        HotelDto hotel = hotelService.updateHotelById(id,hotelDto);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteHotelById(@RequestParam Long id){
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<Void> activateHotelById(@RequestParam Long id){
        hotelService.activateHotelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
