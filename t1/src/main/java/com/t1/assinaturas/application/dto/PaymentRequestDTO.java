package com.t1.assinaturas.application.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private int day;
    private int month;
    private int year;
    private Long codass;
    private BigDecimal paymentValue;
}
