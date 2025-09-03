package com.example.common.events;

public class UserCreatedEvent {
  private String id;
  private String username;
  public String getId() {
    return id;
}
  public void setId(String id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  private String email;
  public UserCreatedEvent() {}
  public UserCreatedEvent(String id, String username, String email) {
    this.id = id; this.username = username; this.email = email;
  }
}
