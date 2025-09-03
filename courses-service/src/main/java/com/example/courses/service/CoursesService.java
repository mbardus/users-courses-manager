package com.example.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courses.model.Course;
import com.example.courses.producer.CourseEventPublisher;
import com.example.courses.repository.CoursesRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoursesService {

    @Autowired
    private CoursesRepository courseRepository;

    @Autowired
    private CourseEventPublisher courseEventPublisher;

    public Mono<Course> createCourse(Course course) {
        return courseRepository.save(course).doOnSuccess(saved -> courseEventPublisher.publishCourseCreated(saved));

    }

    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    public Mono<Course> save(Course course) {
        return courseRepository.save(course).doOnSuccess(saved -> courseEventPublisher.publishCourseCreated(saved));
    }

    public Mono<Course> update(String id, Course course) {
        return courseRepository.findById(id)
                .flatMap(existingCourse -> {
                    existingCourse.setTitle(course.getTitle());
                    existingCourse.setDescription(course.getDescription());
                    return courseRepository.save(existingCourse);
                });
    }

    public Mono<Void> deleteCourse(String id) {
        return courseRepository.deleteById(id);
    }

}
