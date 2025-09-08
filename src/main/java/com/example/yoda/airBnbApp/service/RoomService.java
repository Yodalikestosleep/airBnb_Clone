package com.example.yoda.airBnbApp.service;

import com.example.yoda.airBnbApp.dto.RoomDto;
import com.example.yoda.airBnbApp.entity.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoomService {

    RoomDto createNewRoom(Long hotelId,RoomDto roomDto);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);
    RoomDto getRoomById(Long roomid);
    void deleteRoomById(Long roomId);



}
