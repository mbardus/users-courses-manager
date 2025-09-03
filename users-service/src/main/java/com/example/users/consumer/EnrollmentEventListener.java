package com.example.users.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.common.config.RabbitMQConstants;
import com.example.common.events.EnrollmentCreatedEvent;
import com.example.common.events.EnrollmentDeletedEvent;

@Component
public class EnrollmentEventListener {

  @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_USERS)
    public void onEnrollmentCreated(EnrollmentCreatedEvent evt) {
        System.out.println("[USERS] Enrollment creato per user " + evt.getUserId());
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_DELETED_USERS)
    public void onEnrollmentDeleted(EnrollmentDeletedEvent evt) {
        System.out.println("[USERS] Iscrizione rimossa: " + evt.getUserId() + " → corso " + evt.getCourseId());
    }


}
