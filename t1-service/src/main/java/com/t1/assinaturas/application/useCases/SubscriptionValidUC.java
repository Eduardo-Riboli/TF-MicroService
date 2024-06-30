package com.t1.assinaturas.application.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.domain.services.SubscriptionService;

@Component
public class SubscriptionValidUC {
    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionValidUC(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public ResponseEntity<Boolean> run(Long codass) {
        Boolean status = subscriptionService.checkActiveSubscription(codass);
        return ResponseEntity.status(200).body(status);
    }

    public ResponseEntity<String> getEndDate(Long codass) {
        String date = subscriptionService.getSubscriptionEndDate(codass);
        return ResponseEntity.status(200).body(date);
    }
}
