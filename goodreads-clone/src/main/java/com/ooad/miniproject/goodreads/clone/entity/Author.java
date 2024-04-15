package com.ooad.miniproject.goodreads.clone.entity;
import java.util.*;

public class Author {

    private Long id;
    private String name;
    private List<Book> books;
  
    // Constructors (optional)
    public Author() {}
  
    public Author(Long id, String name, List<Book> books) {
      this.id = id;
      this.name = name;
      this.books = books;
    }
  
    // Getters and setters
    public Long getId() {
      return id;
    }
  
    public void setId(Long id) {
      this.id = id;
    }
  
    public String getName() {
      return name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public List<Book> getBooks() {
      return books;
    }
  
    public void setBooks(List<Book> books) {
      this.books = books;
    }
  
    // Additional methods (optional)
    /*
    public void addBook(Book book) {
      this.books.add(book);
    }
  
    public void removeBook(Book book) {
      this.books.remove(book);
    }
    */
  }
