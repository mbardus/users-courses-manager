package com.example.common.events;

public class CourseDeletedEvent {
    private String id;

    public CourseDeletedEvent() {}

    public CourseDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}