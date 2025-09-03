package com.example.common.events;

public class EnrollmentDeletedEvent {
    private String userId;
    private String courseId;

    public EnrollmentDeletedEvent() {}

    public EnrollmentDeletedEvent(String userId, String courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}