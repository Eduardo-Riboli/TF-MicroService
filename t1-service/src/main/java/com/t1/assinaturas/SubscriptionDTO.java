package com.t1.assinaturas;

import java.util.Date;

public class SubscriptionDTO {
    private Long idSubscription;
    private Date validityDate;

    public SubscriptionDTO(Long idSubscription, Date validityDate) {
        this.idSubscription = idSubscription;
        this.validityDate = validityDate;
    }

    public SubscriptionDTO() {
    }

    public Long getIdSubscription() {
        return idSubscription;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setIdSubscription(Long idSubscription) {
        this.idSubscription = idSubscription;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

}
