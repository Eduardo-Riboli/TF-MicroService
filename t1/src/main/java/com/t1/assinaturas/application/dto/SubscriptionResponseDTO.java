package com.t1.assinaturas.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionResponseDTO {
    private long idSubscription;
    private long idClient;
    private long idApp;
    private Date startDate;
    private Date endDate;
    private String status;
    
}
