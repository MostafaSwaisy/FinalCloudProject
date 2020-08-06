package com.example.finalcloudproject.Models;

public class User {
    String username;
    String id;
    public User() {
    }

    public User(String username, String id) {
        this.username = username;this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
