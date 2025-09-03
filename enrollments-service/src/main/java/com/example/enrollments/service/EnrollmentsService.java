package com.example.enrollments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enrollments.model.Enrollment;
import com.example.enrollments.repository.EnrollmentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnrollmentsService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Mono<Enrollment> createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Flux<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Mono<Enrollment> enroll(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Mono<Void> deleteEnrollment(String id) {
       return enrollmentRepository.deleteById(id);
    }

    public Mono<Enrollment> findById(String id) {
        return enrollmentRepository.findById(id);
    }

}
