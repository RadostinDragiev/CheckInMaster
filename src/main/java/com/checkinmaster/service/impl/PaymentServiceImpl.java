package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Payment;
import com.checkinmaster.model.entity.dto.CreatePaymentDto;
import com.checkinmaster.model.entity.view.CreatePaymentView;
import com.checkinmaster.repository.PaymentRepository;
import com.checkinmaster.service.GuestService;
import com.checkinmaster.service.PaymentService;
import com.checkinmaster.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final GuestService guestService;
    private final ReservationService reservationService;

    @Override
    public CreatePaymentView createPayment(CreatePaymentDto createPaymentDto) {
        Payment payment = this.modelMapper.map(createPaymentDto, Payment.class);
        payment.setGuest(this.guestService.findGuestById(createPaymentDto.getGuestId()));
        payment.setReservation(this.reservationService.getReservationById(createPaymentDto.getReservationId()));

        Payment savedPayment = this.paymentRepository.saveAndFlush(payment);

        return this.modelMapper.map(savedPayment, CreatePaymentView.class);
    }
}
