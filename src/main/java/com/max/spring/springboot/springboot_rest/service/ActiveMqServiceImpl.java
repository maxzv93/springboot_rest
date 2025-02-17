package com.max.spring.springboot.springboot_rest.service;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * AMQ сервис.
 *
 * @author ZuevMYu
 * @since 03.11.2024
 */
public class ActiveMqServiceImpl implements ActiveMqService {
    @Override
    public void enqueueAMQMessage(String message) {

        ActiveMQConnectionFactory connectionFactory = null;
        Connection connection = null;
        try {
            // Create a connection to ActiveMQ JMS broker using AMQP protocol
            connectionFactory = new ActiveMQConnectionFactory();
            connection = connectionFactory.createConnection("admin", "admin");
            connection.start();

            // Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a queue
            Destination destination = session.createQueue("MyFirstQueue");

            // Create a producer specific to queue
            MessageProducer producer = session.createProducer(destination);

            // Создаем объект сообщение
            TextMessage msg = session.createTextMessage(message);
            // Посылаем jms сообщение в очередь
            producer.send(msg);

            // Закрываем соединение
            connection.close();
        } catch (Exception e) {
            //todo добавить логирование
        }

    }
}