package com.example.common.config;

public class RabbitMQConstants {

    public static final String EXCHANGE = "app.exchange";

    // Routing keys - User
    public static final String ROUTING_USER_CREATED = "user.created";
    public static final String ROUTING_USER_UPDATED = "user.updated";
    public static final String ROUTING_USER_DELETED = "user.deleted";

    // Routing keys - Course
    public static final String ROUTING_COURSE_CREATED = "course.created";
    public static final String ROUTING_COURSE_UPDATED = "course.updated";
    public static final String ROUTING_COURSE_DELETED = "course.deleted";

    // Routing keys - Enrollment
    public static final String ROUTING_ENROLLMENT_CREATED = "enrollment.created";
    public static final String ROUTING_ENROLLMENT_DELETED = "enrollment.deleted";

    // Code - User events → Enrollments
    public static final String QUEUE_USER_CREATED_ENROLLMENTS = "user.created.enrollments";
    public static final String QUEUE_USER_UPDATED_ENROLLMENTS = "user.updated.enrollments";
    public static final String QUEUE_USER_DELETED_ENROLLMENTS = "user.deleted.enrollments";

    // Code - Course events → Enrollments
    public static final String QUEUE_COURSE_CREATED_ENROLLMENTS = "course.created.enrollments";
    public static final String QUEUE_COURSE_UPDATED_ENROLLMENTS = "course.updated.enrollments";
    public static final String QUEUE_COURSE_DELETED_ENROLLMENTS = "course.deleted.enrollments";

    // Code - Enrollment events → Users e Courses
    public static final String QUEUE_ENROLLMENT_CREATED_USERS = "enrollment.created.users";
    public static final String QUEUE_ENROLLMENT_CREATED_COURSES = "enrollment.created.courses";
    public static final String QUEUE_ENROLLMENT_DELETED_USERS = "enrollment.deleted.users";
    public static final String QUEUE_ENROLLMENT_DELETED_COURSES = "enrollment.deleted.courses";
}