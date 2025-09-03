package com.example.enrollments.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollments.model.Enrollment;


@Repository
public interface EnrollmentRepository extends ReactiveMongoRepository<Enrollment, String> {

}
