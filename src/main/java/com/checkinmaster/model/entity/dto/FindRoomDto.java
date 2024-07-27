package com.checkinmaster.model.entity.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindRoomDto {

    private String roomType;
    private int capacity;
    private BigDecimal minPricePerNight;
    private BigDecimal maxPricePerNight;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
