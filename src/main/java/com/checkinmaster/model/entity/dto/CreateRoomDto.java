package com.checkinmaster.model.entity.dto;

import com.checkinmaster.model.entity.enums.RoomStatus;
import com.checkinmaster.model.entity.enums.RoomType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomDto {

    private int number;
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private RoomStatus status;
}
