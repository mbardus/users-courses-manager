package com.example.rabbitmq;

import com.example.common.config.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(RabbitMQConstants.EXCHANGE, true, false);
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Il RabbitTemplate viene auto-configurato da Spring Boot.
    // Questo Bean personalizzato assicura che usi il nostro converter JSON.
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter converter) {
        RabbitTemplate tpl = new RabbitTemplate(connectionFactory);
        tpl.setMessageConverter(converter);
        return tpl;
    }
    
    // === Il bean RabbitListenerContainerFactory è stato rimosso ===
    // Spring Boot ne creerà uno di default e userà automaticamente
    // il nostro Jackson2JsonMessageConverter.

    // === Code e binding ===

    // User → Enrollments
    @Bean public Queue userCreatedEnrollmentsQueue() { return new Queue(RabbitMQConstants.QUEUE_USER_CREATED_ENROLLMENTS, true); }
    @Bean public Binding bindUserCreatedEnrollments(Queue userCreatedEnrollmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userCreatedEnrollmentsQueue).to(exchange).with(RabbitMQConstants.ROUTING_USER_CREATED);
    }

    @Bean public Queue userUpdatedEnrollmentsQueue() { return new Queue(RabbitMQConstants.QUEUE_USER_UPDATED_ENROLLMENTS, true); }
    @Bean public Binding bindUserUpdatedEnrollments(Queue userUpdatedEnrollmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userUpdatedEnrollmentsQueue).to(exchange).with(RabbitMQConstants.ROUTING_USER_UPDATED);
    }

    @Bean public Queue userDeletedEnrollmentsQueue() { return new Queue(RabbitMQConstants.QUEUE_USER_DELETED_ENROLLMENTS, true); }
    @Bean public Binding bindUserDeletedEnrollments(Queue userDeletedEnrollmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userDeletedEnrollmentsQueue).to(exchange).with(RabbitMQConstants.ROUTING_USER_DELETED);
    }

    // Course → Enrollments
    @Bean public Queue courseCreatedEnrollmentsQueue() { return new Queue(RabbitMQConstants.QUEUE_COURSE_CREATED_ENROLLMENTS, true); }
    @Bean public Binding bindCourseCreatedEnrollments(Queue courseCreatedEnrollmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(courseCreatedEnrollmentsQueue).to(exchange).with(RabbitMQConstants.ROUTING_COURSE_CREATED);
    }

    @Bean public Queue courseUpdatedEnrollmentsQueue() { return new Queue(RabbitMQConstants.QUEUE_COURSE_UPDATED_ENROLLMENTS, true); }
    @Bean public Binding bindCourseUpdatedEnrollments(Queue courseUpdatedEnrollmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(courseUpdatedEnrollmentsQueue).to(exchange).with(RabbitMQConstants.ROUTING_COURSE_UPDATED);
    }

    @Bean public Queue courseDeletedEnrollmentsQueue() { return new Queue(RabbitMQConstants.QUEUE_COURSE_DELETED_ENROLLMENTS, true); }
    @Bean public Binding bindCourseDeletedEnrollments(Queue courseDeletedEnrollmentsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(courseDeletedEnrollmentsQueue).to(exchange).with(RabbitMQConstants.ROUTING_COURSE_DELETED);
    }

    // Enrollment → Users e Courses
    @Bean public Queue enrollmentCreatedUsersQueue() { return new Queue(RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_USERS, true); }
    @Bean public Binding bindEnrollmentCreatedUsers(Queue enrollmentCreatedUsersQueue, TopicExchange exchange) {
        return BindingBuilder.bind(enrollmentCreatedUsersQueue).to(exchange).with(RabbitMQConstants.ROUTING_ENROLLMENT_CREATED);
    }

    @Bean public Queue enrollmentCreatedCoursesQueue() { return new Queue(RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_COURSES, true); }
    @Bean public Binding bindEnrollmentCreatedCourses(Queue enrollmentCreatedCoursesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(enrollmentCreatedCoursesQueue).to(exchange).with(RabbitMQConstants.ROUTING_ENROLLMENT_CREATED);
    }

    @Bean public Queue enrollmentDeletedUsersQueue() { return new Queue(RabbitMQConstants.QUEUE_ENROLLMENT_DELETED_USERS, true); }
    @Bean public Binding bindEnrollmentDeletedUsers(Queue enrollmentDeletedUsersQueue, TopicExchange exchange) {
        return BindingBuilder.bind(enrollmentDeletedUsersQueue).to(exchange).with(RabbitMQConstants.ROUTING_ENROLLMENT_DELETED);
    }

    @Bean public Queue enrollmentDeletedCoursesQueue() { return new Queue(RabbitMQConstants.QUEUE_ENROLLMENT_DELETED_COURSES, true); }
    @Bean public Binding bindEnrollmentDeletedCourses(Queue enrollmentDeletedCoursesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(enrollmentDeletedCoursesQueue).to(exchange).with(RabbitMQConstants.ROUTING_ENROLLMENT_DELETED);
    }
}
