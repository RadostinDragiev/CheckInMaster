package com.checkinmaster.service;

import com.checkinmaster.model.entity.dto.CreatePaymentDto;
import com.checkinmaster.model.entity.view.CreatePaymentView;

public interface PaymentService {

    CreatePaymentView createPayment(CreatePaymentDto createPaymentDto);
}
