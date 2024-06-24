package com.t1.assinaturas.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.t1.assinaturas.domain.entityModels.SubscriptionModel;

public interface ISubscriptionRepository {

    Optional<SubscriptionModel> findById(Long subscriptionId);

    SubscriptionModel save(SubscriptionModel subscription);

    List<SubscriptionModel> findAll();

    List<SubscriptionModel> findByClientId(Long clientId);

    List<SubscriptionModel> findByAppId(Long appId);

    List<SubscriptionModel> findByClientIdAndAppId(long clientId, long appId);

}
