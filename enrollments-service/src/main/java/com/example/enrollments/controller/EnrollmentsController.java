package com.example.enrollments.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.enrollments.producer.EnrollmentEventPublisher;
import com.example.enrollments.model.Enrollment;
import com.example.enrollments.service.EnrollmentsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class EnrollmentsController {

    @Autowired
    private EnrollmentsService enrollmentsService;

     @Autowired
    private EnrollmentEventPublisher enrollmentEventPublisher;

    @PostMapping("/enrollments")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentsService.createEnrollment(enrollment)
      .doOnSuccess(saved -> enrollmentEventPublisher.publishEnrollmentCreated(saved));
    }

     @GetMapping("/enrollments")
     @ResponseStatus(HttpStatus.OK)
    public Flux<Enrollment> getAllEnrollments() {
        return enrollmentsService.findAll();
    }
    
    @PostMapping("/enrollments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Enrollment> enrollUser(@RequestBody Enrollment enrollment) {
        return enrollmentsService.enroll(enrollment);
    }

    @PutMapping("/enrollments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Enrollment> modifiedUser(@RequestBody Enrollment enrollment) {
        return enrollmentsService.enroll(enrollment);
    }

    @DeleteMapping("/enrollments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteEnrollment(@PathVariable String id) {
        return enrollmentsService.deleteEnrollment(id);
    }

}
