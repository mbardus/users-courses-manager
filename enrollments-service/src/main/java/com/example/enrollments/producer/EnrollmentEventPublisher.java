package com.example.enrollments.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.example.common.config.RabbitMQConstants;
import com.example.common.events.EnrollmentCreatedEvent;
import com.example.common.events.EnrollmentDeletedEvent;
import com.example.enrollments.model.Enrollment;

@Service
public class EnrollmentEventPublisher {

    private final RabbitTemplate tpl;

    public EnrollmentEventPublisher(RabbitTemplate tpl) {
        this.tpl = tpl;
    }

    public void publishEnrollmentCreated(Enrollment e) {
        tpl.convertAndSend(RabbitMQConstants.EXCHANGE,
                           RabbitMQConstants.ROUTING_ENROLLMENT_CREATED,
                           new EnrollmentCreatedEvent(e.getUserId(), e.getCourseId()));
    }

    public void publishEnrollmentDeleted(Enrollment e) {
        tpl.convertAndSend(RabbitMQConstants.EXCHANGE,
                           RabbitMQConstants.ROUTING_ENROLLMENT_DELETED,
                           new EnrollmentDeletedEvent(e.getUserId(), e.getCourseId()));
    }
}