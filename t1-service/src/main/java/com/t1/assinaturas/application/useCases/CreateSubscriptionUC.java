package com.t1.assinaturas.application.useCases;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.t1.assinaturas.application.dto.SubscriptionRequestDTO;
import com.t1.assinaturas.application.dto.SubscriptionResponseDTO;
import com.t1.assinaturas.domain.entityModels.SubscriptionModel;
import com.t1.assinaturas.domain.services.SubscriptionService;

@Component
public class CreateSubscriptionUC {
    private SubscriptionService subscriptionService;

    @Autowired
    public CreateSubscriptionUC(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public ResponseEntity<SubscriptionResponseDTO> run(SubscriptionRequestDTO subscriptionRequest) {
        SubscriptionModel subscription = subscriptionService.createSubscription(subscriptionRequest);
        SubscriptionResponseDTO responseDTO = new SubscriptionResponseDTO(
                subscription.getId(),
                subscription.getClient().getId(),
                subscription.getApp().getId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus() ? "ATIVA" : "CANCELADA");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
