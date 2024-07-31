package com.checkinmaster.web;

import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.dto.FindRoomDto;
import com.checkinmaster.model.entity.view.CreateRoomView;
import com.checkinmaster.model.entity.view.DetailsRoomView;
import com.checkinmaster.model.entity.view.ReservationRoomView;
import com.checkinmaster.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    private final RoomService roomService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createRoom(@ModelAttribute CreateRoomDto createRoomDto,
                                           @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                           UriComponentsBuilder uriComponentsBuilder) throws IOException {
        CreateRoomView room = this.roomService.createRoom(createRoomDto, files);
        return ResponseEntity.created(
                        uriComponentsBuilder.path("/rooms/{UUID}")
                                .buildAndExpand(room.getUuid())
                                .toUri())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationRoomView>> findByCriteria(@RequestBody FindRoomDto findRoomDto) {
        return ResponseEntity.ok(this.roomService.findAllAvailableRooms(findRoomDto));
    }

    @GetMapping("/{roomUUID}")
    public ResponseEntity<DetailsRoomView> getRoomById(@PathVariable UUID roomUUID) {
        DetailsRoomView roomById = this.roomService.getRoomViewById(roomUUID);
        return ResponseEntity.ok(roomById);
    }

    @GetMapping("/getAll")
    private ResponseEntity<List<CreateRoomView>> getAllRoom() {
        return ResponseEntity.ok(this.roomService.getAll());
    }

    @DeleteMapping("/{roomUUID}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable UUID roomUUID) throws Exception {
        this.roomService.deleteRoomById(roomUUID);
        return ResponseEntity.ok().build();
    }
}
