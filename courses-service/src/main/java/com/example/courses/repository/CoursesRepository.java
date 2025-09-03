package com.example.courses.repository;

import com.example.courses.model.Course;

import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends ReactiveMongoRepository<Course, String> {
    Mono<Course> findByTitle(String title);
}
