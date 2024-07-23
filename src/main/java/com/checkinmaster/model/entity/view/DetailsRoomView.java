package com.checkinmaster.model.entity.view;

import com.checkinmaster.model.entity.Reservation;
import com.checkinmaster.model.entity.enums.RoomStatus;
import com.checkinmaster.model.entity.enums.RoomType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsRoomView {

    private UUID uuid;
    private int number;
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private RoomStatus status;
    private List<Reservation> reservations;
}
