package com.checkinmaster.service.impl;

import com.checkinmaster.repository.PaymentRepository;
import com.checkinmaster.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
}
