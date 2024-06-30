package com.engsoft2.cache_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    public static final String QUEUENAME = "conversions.v1.conversion-request.save-history";
    private static Logger logger = LogManager.getLogger(Receiver.class);

    @RabbitListener(queues = QUEUENAME)
    public void receive(SubscriptionDTO dto) {
        logger.info("Mensagem recebida com a atualização da assinatura: {}", dto);

        // colocar na cache.

        logger.info("Data do fim da assinatura guardada na cache.");
    }
}