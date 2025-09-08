package com.example.yoda.airBnbApp.service;

import com.example.yoda.airBnbApp.dto.HotelDto;
import com.example.yoda.airBnbApp.entity.Hotel;
import com.example.yoda.airBnbApp.entity.Room;
import com.example.yoda.airBnbApp.exception.ResourceNotFoundException;
import com.example.yoda.airBnbApp.repository.HotelRepository;
import com.example.yoda.airBnbApp.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //This will create a constructor for me with required arguments
@Slf4j //This annotation will create a default logger for me
public class HotelServiceImpl implements HotelService {
    private final InventoryRepository inventoryRepository;


    private final HotelRepository hotelRepository;  //I didnt had to create a constructor because of the RequiredArgsConstructor annotation
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;



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

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with id : {}" + id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+id));
        modelMapper.map(hotelDto,hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    @Transactional
    public Boolean deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+id));
        hotelRepository.deleteById(id);

        for(Room room : hotel.getRooms()){
            inventoryService.deleteFutureInventories(room);
        }
        //delete the future inventory and not the hotel itself
        return true;
    }

    @Override
    @Transactional
    public void activateHotelById(Long id) {
        log.info("Activating hotel with id : {}"+ id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+id));
        hotel.setActive(true);
        //assuming only doing it once
        for(Room room : hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }



    }
}
