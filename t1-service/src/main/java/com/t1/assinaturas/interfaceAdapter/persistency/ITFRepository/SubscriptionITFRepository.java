package com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t1.assinaturas.domain.entityModels.SubscriptionModel;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Subscription;

public interface SubscriptionITFRepository extends JpaRepository<Subscription, Long> {
    List<SubscriptionModel> findByStatus(Boolean status);

    List<SubscriptionModel> findByClientId(Long clientId);

    List<SubscriptionModel> findByAppId(Long appId);

    List<SubscriptionModel> findByClientIdAndAppId(Long clientId, Long appId);
}
