package com.t1.assinaturas.domain.entityModels;

import java.math.BigDecimal;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppModel {
    private long id;
    private String name;
    private BigDecimal monthlyCost;
}
