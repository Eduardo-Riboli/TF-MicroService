package com.t1.assinaturas.application.useCases;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.application.dto.PaymentRequestDTO;
import com.t1.assinaturas.application.dto.PaymentResponseDTO;
import com.t1.assinaturas.domain.services.PaymentService;

import com.t1.assinaturas.SubscriptionDTO;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.FanoutExchange;

@Component
public class RegisterPaymentUC {
    private PaymentService paymentService;
    private RabbitTemplate rabbitTemplate;
    private FanoutExchange fanout;

    @Autowired
    public RegisterPaymentUC(PaymentService paymentService, RabbitTemplate rabbitTemplate,
            FanoutExchange fanout) {
        this.paymentService = paymentService;
        this.rabbitTemplate = rabbitTemplate;
        this.fanout = fanout;
    }

    public ResponseEntity<?> run(PaymentRequestDTO paymentRequest, RabbitTemplate rabbitTemplate,
            FanoutExchange fanout) {
        int day = paymentRequest.getDay();
        int month = paymentRequest.getMonth() - 1;
        int year = paymentRequest.getYear();
        Long codass = paymentRequest.getCodass();
        BigDecimal valorPago = paymentRequest.getPaymentValue();

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date date = c.getTime();

        PaymentResponseDTO paymentResponse = paymentService.registerPayment(date, codass, valorPago);

        if ("VALOR_INCORRETO".equals(paymentResponse.getStatus())) {
            return ResponseEntity.badRequest().body(paymentResponse);

        } else if ("PAGAMENTO_OK".equals(paymentResponse.getStatus())) {

            SubscriptionDTO dto = new SubscriptionDTO(codass, paymentResponse.getDate());
            this.rabbitTemplate.convertAndSend(this.fanout.getName(), "", dto);

            return ResponseEntity.ok(paymentResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": " + paymentResponse + "}");
        }
    }
}
