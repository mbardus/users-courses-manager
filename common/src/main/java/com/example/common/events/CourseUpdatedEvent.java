package com.example.common.events;

public class CourseUpdatedEvent {
    private String id;
    private String nome;

    public CourseUpdatedEvent() {}

    public CourseUpdatedEvent(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}