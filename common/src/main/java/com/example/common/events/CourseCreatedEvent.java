package com.example.common.events;

public class CourseCreatedEvent {
    private String id;
    private String nome;
    public CourseCreatedEvent() {}
    public CourseCreatedEvent(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}