package com.max.spring.springboot.springboot_rest.service;


/**
 * Сервис для взаимодействия с ActiveMq.
 *
 * @author ZuevMYu
 * @since 25.07.2024
 */
public interface KafkaService {

    /**
     * Метод отправки сообщений в брокер kafka.
     *
     * @param message - сообщение.
     */
    public void enqueueKafkaMessage(String message);
}

