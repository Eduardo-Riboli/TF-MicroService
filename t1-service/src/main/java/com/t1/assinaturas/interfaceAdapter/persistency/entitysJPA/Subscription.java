package com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA;

import java.util.Date;

import com.t1.assinaturas.domain.entityModels.SubscriptionModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private App app;
    @ManyToOne
    private Client client;
    private Date startDate;
    private Date endDate;
    private Boolean status;

    public Subscription() {}

    public Subscription(long id, App app, Client client, Date startDate, Date endDate, Boolean status) {
        this.id = id;
        this.app = app;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public static Subscription fromSubscriptionModel(SubscriptionModel subscriptionModel) {
        return new Subscription(subscriptionModel.getId(), App.fromAppModel(subscriptionModel.getApp()),
                Client.fromClientModel(subscriptionModel.getClient()),
                subscriptionModel.getStartDate(), subscriptionModel.getEndDate(), subscriptionModel.getStatus());
    }

    public static SubscriptionModel toSubscriptionModel(Subscription subscription) {
        return new SubscriptionModel(subscription.getId(), App.toAppModel(subscription.getApp()),
                Client.toClientModel(subscription.getClient()),
                subscription.getStartDate(), subscription.getEndDate(), subscription.getStatus());
    }
}
