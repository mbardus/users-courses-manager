package com.example.common.events;

public class UserDeletedEvent {
    private String id;

    public UserDeletedEvent() {}

    public UserDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}