package com.checkinmaster.service;

import com.checkinmaster.model.entity.Room;
import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.dto.FindRoomDto;
import com.checkinmaster.model.entity.view.CreateRoomView;
import com.checkinmaster.model.entity.view.DetailsRoomView;
import com.checkinmaster.model.entity.view.ReservationRoomView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface RoomService {

    CreateRoomView createRoom(CreateRoomDto createRoomDto, List<MultipartFile> multipartFiles) throws IOException;

    DetailsRoomView getRoomViewById(UUID uuid);

    Room getRoomById(UUID uuid);

    List<CreateRoomView> getAll();

    List<ReservationRoomView> findAllAvailableRooms(FindRoomDto findRoomDto);

    void deleteRoomById(UUID uuid);
}
