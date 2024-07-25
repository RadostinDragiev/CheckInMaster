package com.checkinmaster.web;

import com.checkinmaster.model.entity.dto.CreatePaymentDto;
import com.checkinmaster.model.entity.view.CreatePaymentView;
import com.checkinmaster.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<CreatePaymentView> createPayment(@RequestBody CreatePaymentDto createPaymentDto,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        CreatePaymentView payment = this.paymentService.createPayment(createPaymentDto);

        return ResponseEntity.created(
                        uriComponentsBuilder.path("/payments/{UUID}")
                                .buildAndExpand(payment.getUuid())
                                .toUri())
                .build();
    }
}
