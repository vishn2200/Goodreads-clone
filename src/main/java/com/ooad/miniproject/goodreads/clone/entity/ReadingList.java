package com.ooad.miniproject.goodreads.clone.entity;

import java.util.List;

public class ReadingList {
    private Long id;
    public User user;
    public List<Book> books;

    // Constructors
    public ReadingList() {
    }

    public ReadingList(User user, List<Book> books) {
        this.user = user;
        this.books = books;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
