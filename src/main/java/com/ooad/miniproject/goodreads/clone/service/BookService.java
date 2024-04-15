package com.ooad.miniproject.goodreads.clone.service;

import com.ooad.miniproject.goodreads.clone.repository.BookRepository;
import com.ooad.miniproject.goodreads.clone.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String query) {
        return bookRepository.searchBooks(query);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    // Other service methods
}
