package com.t1.assinaturas.domain.entityModels;

import java.util.Date;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionModel {
    private long id;
    private AppModel app;
    private ClientModel client;
    private Date startDate;
    private Date endDate;
    private Boolean status;
}
