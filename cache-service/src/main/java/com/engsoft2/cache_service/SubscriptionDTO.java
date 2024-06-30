package com.engsoft2.cache_service;

import java.util.Date;

public class SubscriptionDTO {
    private int idSubscription;
    private Date validityDate;

    public SubscriptionDTO(int idSubscription, Date validityDate) {
        this.idSubscription = idSubscription;
        this.validityDate = validityDate;
    }

    public SubscriptionDTO() {
    }

    public int getIdSubscription() {
        return idSubscription;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }
}
