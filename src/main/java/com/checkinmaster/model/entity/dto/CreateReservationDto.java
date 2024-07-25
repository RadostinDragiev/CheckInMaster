package com.checkinmaster.model.entity.dto;

import com.checkinmaster.model.entity.enums.PaymentType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    private UUID roomUUID;
    private CreateGuestDto guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private PaymentType paymentType;
    private int guestsCount;
    private List<CreatePaymentDto> payments;
}
