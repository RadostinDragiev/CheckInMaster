package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Room;
import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.view.CreateRoomView;
import com.checkinmaster.model.entity.view.DetailsRoomView;
import com.checkinmaster.repository.RoomRepository;
import com.checkinmaster.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreateRoomView createRoom(CreateRoomDto createRoomDto) {
        Room room = this.modelMapper.map(createRoomDto, Room.class);
        Room newlyCreatedRoom = this.roomRepository.saveAndFlush(room);

        return this.modelMapper.map(newlyCreatedRoom, CreateRoomView.class);
    }

    @Override
    public DetailsRoomView getRoomById(UUID uuid) {
        return this.modelMapper.map(this.roomRepository.findById(uuid), DetailsRoomView.class);
    }

    @Override
    public void deleteRoomById(UUID uuid) {
        this.roomRepository.deleteById(uuid);
    }
}
