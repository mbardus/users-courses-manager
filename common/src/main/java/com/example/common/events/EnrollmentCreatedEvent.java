package com.example.common.events;

public class EnrollmentCreatedEvent {

    private String id;
    private String userId;
    private String courseId;
    public EnrollmentCreatedEvent() {}
    public EnrollmentCreatedEvent(String userId, String courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }
    public String getCourseId() {
        return courseId;
    }
    public String getId() {
        return id;
    }
}