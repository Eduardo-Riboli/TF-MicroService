package com.t1.assinaturas.interfaceAdapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t1.assinaturas.application.dto.PaymentRequestDTO;
import com.t1.assinaturas.application.useCases.RegisterPaymentUC;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.FanoutExchange;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/registrarpagamento")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private RegisterPaymentUC registerPaymentUC;

    @PostMapping("")
    public ResponseEntity<?> registerPayment(@RequestBody PaymentRequestDTO paymentRequest,
            RabbitTemplate rabbitTemplate, FanoutExchange fanout) {
        return registerPaymentUC.run(paymentRequest, rabbitTemplate, fanout);
    }
}
