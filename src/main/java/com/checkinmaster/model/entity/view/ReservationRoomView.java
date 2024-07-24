package com.checkinmaster.model.entity.view;

import com.checkinmaster.model.entity.enums.RoomType;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRoomView {

    private UUID uuid;
    private int number;
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private int capacity;
}
