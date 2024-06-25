package com.t1.assinaturas.application.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.application.dto.SubscriptionResponseDTO;
import com.t1.assinaturas.domain.entityModels.SubscriptionModel;
import com.t1.assinaturas.domain.services.SubscriptionService;

@Component
public class SubscriptionAppUC {
    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionAppUC(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public ResponseEntity<List<SubscriptionResponseDTO>> run(Long appID) {
        List<SubscriptionModel> listSubscriptions = subscriptionService.getSubscriptionByAppId(appID);
        List<SubscriptionResponseDTO> listDTO = listSubscriptions.stream()
                .map(subscription -> new SubscriptionResponseDTO(
                        subscription.getId(),
                        subscription.getClient().getId(),
                        subscription.getApp().getId(),
                        subscription.getStartDate(),
                        subscription.getEndDate(),
                        subscription.getStatus() ? "ATIVA" : "CANCELADA"))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }
}
