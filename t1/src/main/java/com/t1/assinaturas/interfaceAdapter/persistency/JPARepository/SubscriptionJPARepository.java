package com.t1.assinaturas.interfaceAdapter.persistency.JPARepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t1.assinaturas.domain.entityModels.SubscriptionModel;
import com.t1.assinaturas.domain.repositories.ISubscriptionRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository.AppITFRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository.ClientITFRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.ITFRepository.SubscriptionITFRepository;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.App;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Client;
import com.t1.assinaturas.interfaceAdapter.persistency.entitysJPA.Subscription;

@Repository
public class SubscriptionJPARepository implements ISubscriptionRepository {
    private SubscriptionITFRepository subscription;
    private ClientITFRepository client;
    private AppITFRepository app;

    @Autowired
    public SubscriptionJPARepository(SubscriptionITFRepository subscription, ClientITFRepository client,
            AppITFRepository app) {
        this.subscription = subscription;
        this.client = client;
        this.app = app;
    }

    @Override
    public Optional<SubscriptionModel> findById(Long subscriptionId) {
        Optional<Subscription> newSubscription = subscription.findById(subscriptionId);

        if (newSubscription.isPresent()) {
            return Optional.of(Subscription.toSubscriptionModel(newSubscription.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public SubscriptionModel save(SubscriptionModel subscriptionModel) {
        Subscription newSubscription = Subscription.fromSubscriptionModel(subscriptionModel);
        Subscription savedSubscription = subscription.save(newSubscription);
        return Subscription.toSubscriptionModel(savedSubscription);
    }

    @Override
    public List<SubscriptionModel> findAll() {
        List<Subscription> subscriptions = subscription.findAll();

        return subscriptions.stream()
                .map(it -> Subscription.toSubscriptionModel(it))
                .toList();
    }

    @Override
    public List<SubscriptionModel> findByClientId(Long clientId) {
        Optional<Client> newClient = client.findById(clientId);

        if (newClient.isPresent()) {
            List<Subscription> subscriptions = subscription.findAll();

            return subscriptions.stream()
                    .filter(it -> it.getClient().equals(newClient.get()))
                    .map(it -> Subscription.toSubscriptionModel(it))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<SubscriptionModel> findByAppId(Long appId) {
        Optional<App> newApp = app.findById(appId);

        if (newApp.isPresent()) {
            List<Subscription> subscriptions = subscription.findAll();

            return subscriptions.stream()
                    .filter(it -> it.getApp().equals(newApp.get()))
                    .map(it -> Subscription.toSubscriptionModel(it))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<SubscriptionModel> findByClientIdAndAppId(long clientId, long appId) {
        Optional<Client> newClient = client.findById(clientId);
        Optional<App> newApp = app.findById(appId);

        if (newApp.isPresent()) {
            List<Subscription> subscriptions = subscription.findAll();

            return subscriptions.stream()
                    .filter(it -> it.getApp().equals(newApp.get()) && it.getClient().equals(newClient.get()))
                    .map(it -> Subscription.toSubscriptionModel(it))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
