package com.ooad.miniproject.goodreads.clone.recommendation;

import com.ooad.miniproject.goodreads.clone.entity.Book;

import java.util.List;

public interface RecommendationStrategy {
    List<Book> getRecommendations(String input);
}