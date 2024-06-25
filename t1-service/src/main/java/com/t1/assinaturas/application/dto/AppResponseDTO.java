package com.t1.assinaturas.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AppResponseDTO {
    private long id;
    private String name;
    private BigDecimal monthly_cost;
}
