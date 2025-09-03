package com.example.courses.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.courses.model.Course;
import com.example.courses.producer.CourseEventPublisher;
import com.example.courses.service.CoursesService;

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
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private CourseEventPublisher courseEventPublisher;

    @GetMapping("/courses")
    public Flux<Course> getAllCourses() {
        return coursesService.findAll();
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Course> createCourse(@RequestBody Course course) {
        return coursesService.save(course)
      .doOnSuccess(saved -> courseEventPublisher.publishCourseCreated(saved));
    }
    
    @PutMapping("/{id}")
    public Mono<Course> updateCourse(@PathVariable String id, @RequestBody Course course) {
        return coursesService.update(id, course)
        .doOnSuccess(updated -> courseEventPublisher.publishCourseUpdated(updated));
    }

    @DeleteMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCourse(@PathVariable String id) {
        return coursesService.deleteCourse(id)
        .doOnSuccess(deleted -> courseEventPublisher.publishCourseDeleted(id));
    }

}
