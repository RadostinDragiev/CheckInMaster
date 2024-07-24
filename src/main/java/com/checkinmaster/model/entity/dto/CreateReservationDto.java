package com.checkinmaster.model.entity.dto;

import com.checkinmaster.model.entity.enums.PaymentType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    private int roomId;
    private UUID roomUUID;
    private CreateGuestDto guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private PaymentType paymentType;
    private int guestsCount;
}
