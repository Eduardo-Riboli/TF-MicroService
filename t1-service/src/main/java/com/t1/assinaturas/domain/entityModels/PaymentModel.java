package com.t1.assinaturas.domain.entityModels;

import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentModel {
    private long id;
    private SubscriptionModel subscription;
    private BigDecimal amountPaid;
    private Date paymentDate;
    private String promotionCode;
}
