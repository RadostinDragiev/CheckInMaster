package com.checkinmaster.model.entity.dto;

import com.checkinmaster.model.entity.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDto {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private UUID reservationId;
    private UUID guestId;
}
