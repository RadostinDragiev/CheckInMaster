package com.checkinmaster.web;

import com.checkinmaster.model.entity.Room;
import com.checkinmaster.model.entity.dto.CreateRoomDto;
import com.checkinmaster.model.entity.enums.RoomStatus;
import com.checkinmaster.model.entity.enums.RoomType;
import com.checkinmaster.repository.RoomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    private static final int ROOM_NUMBER = 1;
    private static final RoomType ROOM_TYPE = RoomType.DOUBLE;
    private static final int CAPACITY = 2;
    private static final BigDecimal PRICE_PER_NIGHT = BigDecimal.valueOf(120.0);
    private static final RoomStatus ROOM_STATUS = RoomStatus.AVAILABLE;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        this.roomRepository.deleteAll();
    }

    @Test
    void createRoomShouldReturn201() throws Exception {
        CreateRoomDto createRoomDto = CreateRoomDto.builder()
                .number(ROOM_NUMBER)
                .roomType(ROOM_TYPE)
                .capacity(CAPACITY)
                .pricePerNight(PRICE_PER_NIGHT)
                .status(ROOM_STATUS)
                .build();

        this.mockMvc.perform(post("/rooms")
                        .contentType(APPLICATION_JSON)
                        .content(json(createRoomDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getRoomShouldReturnOK() throws Exception {
        Room room = createRoomInDB();

        this.mockMvc.perform(get("/rooms/{uuid}", room.getUuid())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.number").value(ROOM_NUMBER))
                .andExpect(jsonPath("$.roomType").value(ROOM_TYPE.toString()))
                .andExpect(jsonPath("$.pricePerNight").value(PRICE_PER_NIGHT))
                .andExpect(jsonPath("$.capacity").value(CAPACITY))
                .andExpect(jsonPath("$.status").value(ROOM_STATUS.toString()))
                .andExpect(jsonPath("$.reservations").isArray());
    }

    @Test
    void deleteRoomShouldReturnOK() throws Exception {
        Room room = createRoomInDB();

        this.mockMvc.perform(delete("/rooms/{uuid}", room.getUuid())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Room createRoomInDB() {
        Room room = Room.builder()
                .number(ROOM_NUMBER)
                .roomType(ROOM_TYPE)
                .capacity(CAPACITY)
                .pricePerNight(PRICE_PER_NIGHT)
                .status(ROOM_STATUS)
                .build();

        return this.roomRepository.saveAndFlush(room);
    }

    private static String json(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}