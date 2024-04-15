package com.ooad.miniproject.goodreads.clone.recommendation;

import com.ooad.miniproject.goodreads.clone.entity.Book;
import com.ooad.miniproject.goodreads.clone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorBasedRecommendationStrategy implements RecommendationStrategy {
    private final BookService bookService;

    @Autowired
    public AuthorBasedRecommendationStrategy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getRecommendations(String author) {
        List<Book> allBooks = bookService.getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .sorted((b1, b2) -> Double.compare(b2.getRating(), b1.getRating()))
                .limit(3)
                .collect(Collectors.toList());
    }
}