package com.ooad.miniproject.goodreads.clone.recommendation;

import com.ooad.miniproject.goodreads.clone.entity.Book;
import com.ooad.miniproject.goodreads.clone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RandomRecommendationStrategy implements RecommendationStrategy {
    private final BookService bookService;

    @Autowired
    public RandomRecommendationStrategy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getRecommendations(String input) {
        List<Book> allBooks = bookService.getAllBooks();
        return allBooks.stream()
                .sorted((b1, b2) -> Double.compare(Math.random(), Math.random()))
                .limit(3)
                .collect(Collectors.toList());
    }
}