package com.t1.assinaturas.domain.services;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t1.assinaturas.application.dto.SubscriptionRequestDTO;
import com.t1.assinaturas.domain.entityModels.AppModel;
import com.t1.assinaturas.domain.entityModels.ClientModel;
import com.t1.assinaturas.domain.entityModels.SubscriptionModel;
import com.t1.assinaturas.domain.repositories.IAppRepository;
import com.t1.assinaturas.domain.repositories.IClientRepository;
import com.t1.assinaturas.domain.repositories.ISubscriptionRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private ISubscriptionRepository subscriptionRepository;
    private IClientRepository clientRepository;
    private IAppRepository appRepository;

    @Autowired
    public SubscriptionService(IAppRepository appRepository, ISubscriptionRepository subscriptionRepository,
            IClientRepository clientRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.appRepository = appRepository;
        this.clientRepository = clientRepository;
    }

    public List<SubscriptionModel> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<SubscriptionModel> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public SubscriptionModel updateSubscription(SubscriptionModel subscription) {
        return subscriptionRepository.save(subscription);
    }

    public List<SubscriptionModel> getSubscriptionByStatus(String type) {
        Date now = new Date();
        List<SubscriptionModel> subscriptions;
        switch (type.toUpperCase()) {
            case "ATIVAS":
                subscriptions = subscriptionRepository.findAll().stream()
                        .filter(subscription -> subscription.getEndDate().after(now) && subscription.getStatus())
                        .collect(Collectors.toList());
                break;
            case "CANCELADAS":
                subscriptions = subscriptionRepository.findAll().stream()
                        .filter(subscription -> !subscription.getStatus())
                        .collect(Collectors.toList());
                break;
            case "TODAS":
                subscriptions = subscriptionRepository.findAll();
                break;
            default:
                subscriptions = List.of(); // Return an empty list for an unknown type
                break;
        }
        return subscriptions;
    }

    @Transactional(readOnly = true)
    public List<SubscriptionModel> getSubscriptionByClientId(Long clientId) {
        return subscriptionRepository.findByClientId(clientId);
    }

    @Transactional(readOnly = true)
    public List<SubscriptionModel> getSubscriptionByAppId(Long appId) {
        return subscriptionRepository.findByAppId(appId);
    }

    public Boolean checkActiveSubscription(Long id) {
        return this.getSubscriptionById(id).map(SubscriptionModel::getStatus).orElse(false);
    }

    public String getSubscriptionEndDate(Long id) {
        return this.getSubscriptionById(id).map(subscription -> subscription.getEndDate().toString()).orElse("");
    }

    @Transactional
    public SubscriptionModel createSubscription(SubscriptionRequestDTO subscriptionRequest) {
        AppModel app = appRepository.findById(subscriptionRequest.getCodigoAplicativo()).orElse(null);
        ClientModel client = clientRepository.findById(subscriptionRequest.getCodigoCliente()).orElse(null);

        if (app == null || client == null) {
            throw new IllegalArgumentException("Cliente ou aplicativo não encontrado.");
        }

        List<SubscriptionModel> subscriptionList = subscriptionRepository.findByClientIdAndAppId(client.getId(),
                app.getId());
        SubscriptionModel subscription;

        if (!subscriptionList.isEmpty()) {
            SubscriptionModel existingSubscription = subscriptionList.get(0);
            if (!existingSubscription.getStatus()) {
                existingSubscription.setStatus(true);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                existingSubscription.setStartDate(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 30);
                existingSubscription.setEndDate(calendar.getTime());
                subscription = subscriptionRepository.save(existingSubscription);
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(existingSubscription.getEndDate());
                calendar.add(Calendar.DAY_OF_MONTH, 30);
                existingSubscription.setEndDate(calendar.getTime());
                subscription = subscriptionRepository.save(existingSubscription);
            }
        } else {
            subscription = new SubscriptionModel();
            subscription.setApp(app);
            subscription.setClient(client);
            subscription.setStartDate(new Date());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 37);
            subscription.setEndDate(calendar.getTime());
            subscription.setStatus(true);

            subscription = subscriptionRepository.save(subscription);
        }

        return subscription;
    }

}
