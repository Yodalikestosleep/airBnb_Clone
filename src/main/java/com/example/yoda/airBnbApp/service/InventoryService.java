package com.example.yoda.airBnbApp.service;

import com.example.yoda.airBnbApp.dto.RoomDto;
import com.example.yoda.airBnbApp.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
}
