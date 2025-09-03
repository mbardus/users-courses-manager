package com.example.courses.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.common.config.RabbitMQConstants;
import com.example.common.events.EnrollmentCreatedEvent;
import com.example.common.events.EnrollmentDeletedEvent;

@Component
public class EnrollmentEventListener {

  @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_COURSES)
    public void onEnrollmentCreated(EnrollmentCreatedEvent evt) {
        System.out.println("[COURSES] Enrollment creato per corso " + evt.getCourseId());
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_DELETED_COURSES)
    public void onEnrollmentDeleted(EnrollmentDeletedEvent evt) {
        System.out.println("[COURSES] Iscrizione rimossa dal corso " + evt.getCourseId());
    }


}
