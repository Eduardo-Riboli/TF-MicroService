package com.engsoft2.cache_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private static Logger logger = LogManager.getLogger(Receiver.class);

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @RabbitListener(queues = "#{rabbitMQConfig.getQueueName()}")
    public void receive(SubscriptionDTO dto) {
        logger.info("Mensagem recebida com a atualização da assinatura: {}", dto);

        logger.info("Data do fim da assinatura guardada na cache.");
    }
}