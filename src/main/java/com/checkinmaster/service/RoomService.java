package com.checkinmaster.service;

import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.view.CreateRoomView;
import com.checkinmaster.model.entity.view.DetailsRoomView;

import java.util.UUID;

public interface RoomService {

    CreateRoomView createRoom(CreateRoomDto createRoomDto);

    DetailsRoomView getRoomById(UUID uuid);

    void deleteRoomById(UUID uuid);
}
