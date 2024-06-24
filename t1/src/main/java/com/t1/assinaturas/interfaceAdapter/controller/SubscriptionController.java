package com.t1.assinaturas.interfaceAdapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t1.assinaturas.application.dto.SubscriptionRequestDTO;
import com.t1.assinaturas.application.dto.SubscriptionResponseDTO;
import com.t1.assinaturas.application.useCases.CreateSubscriptionUC;
import com.t1.assinaturas.application.useCases.SubscriptionAppUC;
import com.t1.assinaturas.application.useCases.SubscriptionByTypeUC;
import com.t1.assinaturas.application.useCases.SubscriptionClientUC;
import com.t1.assinaturas.application.useCases.SubscriptionValidUC;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/servcad")
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private CreateSubscriptionUC subscriptionUC;
    @Autowired
    private SubscriptionByTypeUC subscriptionByTypeUC;
    @Autowired
    private SubscriptionClientUC subscriptionClientUC;
    @Autowired
    private SubscriptionAppUC subscriptionAppUC;
    @Autowired
    private SubscriptionValidUC subscriptionValidUC;

    @PostMapping("/assinaturas")
    public ResponseEntity<SubscriptionResponseDTO> createSubscription(
            @RequestBody SubscriptionRequestDTO subscriptionRequest) {
        return subscriptionUC.run(subscriptionRequest);
    }

    @GetMapping("/assinaturas/{type}")
    public ResponseEntity<List<SubscriptionResponseDTO>> getSubscriptionsFromType(@PathVariable String type) {
        return subscriptionByTypeUC.run(type);
    }

    @GetMapping("/asscli/{codcli}")
    public ResponseEntity<List<SubscriptionResponseDTO>> getSubscriptionsFromClientID(@PathVariable long codcli) {
        return subscriptionClientUC.run(codcli);
    }

    @GetMapping("/assapp/{codapp}")
    public ResponseEntity<List<SubscriptionResponseDTO>> getSubscriptionsFromAppID(@PathVariable long codapp) {
        return subscriptionAppUC.run(codapp);
    }

    @GetMapping("/assinvalida/{codass}")
    public ResponseEntity<Boolean> checkActiveSubscription(@PathVariable long codass) {
        return subscriptionValidUC.run(codass);
    }
}
