package com.example.yoda.airBnbApp.service;

import com.example.yoda.airBnbApp.dto.RoomDto;
import com.example.yoda.airBnbApp.entity.Hotel;
import com.example.yoda.airBnbApp.entity.Room;
import com.example.yoda.airBnbApp.exception.ResourceNotFoundException;
import com.example.yoda.airBnbApp.repository.HotelRepository;
import com.example.yoda.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating new room in Hotel with If : {}" + hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+hotelId));
        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);

        }

        return modelMapper.map(room,RoomDto.class);
        //Create Inventory as soon as room is created and if hotel is active


    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+hotelId));
        log.info("Getting all rooms in Hotel with if : {}" + hotelId);
        List<RoomDto> rooms = hotel.getRooms() //this returns a list of room entities so we map it to roomDto
                .stream()
                .map((element)->modelMapper.map(element,RoomDto.class))
                .collect(Collectors.toList());
        return rooms;
    }


    @Override
    public RoomDto getRoomById(Long roomid) {
        log.info("Getting room with id: {}"+ roomid);
        Room room = roomRepository.findById(roomid).orElseThrow(()->new ResourceNotFoundException("Room not found with id : "+roomid));
        return modelMapper.map(room,RoomDto.class);


    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with id: {}"+ roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found with id : "+roomId));
        inventoryService.deleteFutureInventories(room);
        log.info("Deleted room with id : {}" + roomId);
        roomRepository.deleteById(roomId);


    }
}
