package com.example.enrollments.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.config.RabbitMQConstants;
import com.example.common.events.CourseCreatedEvent;
import com.example.common.events.CourseDeletedEvent;
import com.example.common.events.CourseUpdatedEvent;
import com.example.common.events.EnrollmentCreatedEvent;
import com.example.common.events.UserCreatedEvent;
import com.example.common.events.UserDeletedEvent;
import com.example.common.events.UserUpdatedEvent;
import com.example.enrollments.model.Enrollment;
import com.example.enrollments.service.EnrollmentsService;

import reactor.core.publisher.Flux;

@Component
public class EnrollmentEventListener {

    @Autowired
    private EnrollmentsService enrollmentsService;

  @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_USERS)
  public void onUsersEnrollmentCreated(EnrollmentCreatedEvent evt) {
    System.out.println("[USERS] Enrollment creato: " + evt.getId() + " per user " + evt.getUserId());
  }
  @RabbitListener(queues = RabbitMQConstants.QUEUE_ENROLLMENT_CREATED_COURSES)
  public void onCoursesEnrollmentCreated(EnrollmentCreatedEvent evt) {
    System.out.println("[COURSES] Enrollment creato per corso " + evt.getCourseId());
  }
  @RabbitListener(queues = RabbitMQConstants.QUEUE_USER_CREATED_ENROLLMENTS)
    public void onUserCreated(UserCreatedEvent evt) {
        System.out.println("[ENROLLMENTS] Utente creato: " + evt.getId());
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_USER_UPDATED_ENROLLMENTS)
    public void onUserUpdated(UserUpdatedEvent evt) {
        System.out.println("[ENROLLMENTS] Utente aggiornato: " + evt.getId());
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_USER_DELETED_ENROLLMENTS)
    public void onUserDeleted(UserDeletedEvent evt) {
        System.out.println("[ENROLLMENTS] Utente rimosso: " + evt.getId());
        Flux<Enrollment> enrollments = enrollmentsService.findAll();
        Flux.fromIterable(enrollments.collectList().block()).flatMap(
            e -> e.getUserId().equalsIgnoreCase(evt.getId()) ?
            enrollmentsService.deleteEnrollment(e.getId()):null
            ).subscribe();
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_COURSE_CREATED_ENROLLMENTS)
    public void onCourseCreated(CourseCreatedEvent evt) {
        System.out.println("[ENROLLMENTS] Corso creato: " + evt.getId());
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_COURSE_UPDATED_ENROLLMENTS)
    public void onCourseUpdated(CourseUpdatedEvent evt) {
        System.out.println("[ENROLLMENTS] Corso aggiornato: " + evt.getId());
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE_COURSE_DELETED_ENROLLMENTS)
    public void onCourseDeleted(CourseDeletedEvent evt) {
        System.out.println("[ENROLLMENTS] Corso rimosso: " + evt.getId());
        Flux<Enrollment> enrollments = enrollmentsService.findAll();
        Flux.fromIterable(enrollments.collectList().block()).flatMap(
            e -> e.getCourseId().equalsIgnoreCase(evt.getId()) ?
            enrollmentsService.deleteEnrollment(e.getId()):null
            ).subscribe();

    }

}
