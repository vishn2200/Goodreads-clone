package com.ooad.miniproject.goodreads.clone.entity;
import java.io.*;

public class User implements Serializable{

    private Long id;
    public String username;
    public String email;
    public String password;
    public Long reading_list_id;

    public User() {
    }

    // Constructor with parameters
    public User(String username, String password, String email, Long reading_list_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.reading_list_id=reading_list_id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReadingListId() {
        return this.reading_list_id;
    }

    public void setReadingListId(Long reading_list_id) {
        this.reading_list_id = reading_list_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
