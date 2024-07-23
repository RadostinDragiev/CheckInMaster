package com.checkinmaster.web;

import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.view.CreateRoomView;
import com.checkinmaster.model.entity.view.DetailsRoomView;
import com.checkinmaster.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<CreateRoomView> createRoom(@RequestBody CreateRoomDto createRoomDto,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        CreateRoomView room = this.roomService.createRoom(createRoomDto);
        return ResponseEntity.created(
                        uriComponentsBuilder.path("/rooms/{UUID}")
                                .buildAndExpand(room.getUuid())
                                .toUri())
                .build();
    }

    @GetMapping("/{roomUUID}")
    public ResponseEntity<DetailsRoomView> getRoomById(@PathVariable UUID roomUUID) {
        DetailsRoomView roomById = this.roomService.getRoomById(roomUUID);
        return ResponseEntity.ok(roomById);
    }

    @DeleteMapping("/{roomUUID}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable UUID roomUUID) {
        this.roomService.deleteRoomById(roomUUID);
        return ResponseEntity.ok().build();
    }
}
