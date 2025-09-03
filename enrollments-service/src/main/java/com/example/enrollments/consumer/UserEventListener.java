package com.example.enrollments.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.common.config.RabbitMQConstants;
import com.example.enrollments.model.User;

@Service
public class UserEventListener {

    @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_USERS)
    public void handleUserCreated(User user) {
        System.out.println("Nuovo utente ricevuto: " + user.getId());
        // logica di business per preparare l'enrollment
    }
}