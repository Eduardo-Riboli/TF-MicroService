package com.t1.assinaturas.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponseDTO {
    @JsonProperty("status")
    String status;
    @JsonProperty("data")
    Date date;
    @JsonProperty("valor_estornado")
    BigDecimal value;
}