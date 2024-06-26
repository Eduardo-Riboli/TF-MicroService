package com.engsoft2.cache_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static Logger logger = LogManager.getLogger(Receiver.class);

    @RabbitListener(queues = "#{RabbitMQConfig.getQueueName()}")
    public void receive(HistoryDTO dto) {
        logger.info("Mensagem recebida: {}", dto);
    }
}