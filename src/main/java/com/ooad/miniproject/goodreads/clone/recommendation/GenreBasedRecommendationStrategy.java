package com.ooad.miniproject.goodreads.clone.recommendation;

import com.ooad.miniproject.goodreads.clone.entity.Book;
import com.ooad.miniproject.goodreads.clone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreBasedRecommendationStrategy implements RecommendationStrategy {
    private final BookService bookService;

    @Autowired
    public GenreBasedRecommendationStrategy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> getRecommendations(String genre) {
        List<Book> allBooks = bookService.getAllBooks();
        List<String> targetGenres = Arrays.asList(genre.split("/"));
        
        return allBooks.stream()
                .filter(book -> {
                    List<String> bookGenres = Arrays.asList(book.getGenre().split("/"));
                    return bookGenres.stream().anyMatch(targetGenres::contains);
                })
                .sorted((b1, b2) -> Double.compare(b2.getRating(), b1.getRating()))
                .limit(3)
                .collect(Collectors.toList());
    }
}
