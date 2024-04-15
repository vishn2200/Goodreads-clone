package com.ooad.miniproject.goodreads.clone.recommendation;

import com.ooad.miniproject.goodreads.clone.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationContext {
    private final GenreBasedRecommendationStrategy genreBasedStrategy;
    private final AuthorBasedRecommendationStrategy authorBasedStrategy;
    private final RandomRecommendationStrategy randomStrategy;

    @Autowired
    public RecommendationContext(GenreBasedRecommendationStrategy genreBasedStrategy,
                                 AuthorBasedRecommendationStrategy authorBasedStrategy,
                                 RandomRecommendationStrategy randomStrategy) {
        this.genreBasedStrategy = genreBasedStrategy;
        this.authorBasedStrategy = authorBasedStrategy;
        this.randomStrategy = randomStrategy;
    }

    public List<Book> getRecommendations(String input, RecommendationType recommendationType) {
        RecommendationStrategy strategy;
        switch (recommendationType) {
            case GENRE_BASED:
                strategy = genreBasedStrategy;
                break;
            case AUTHOR_BASED:
                strategy = authorBasedStrategy;
                break;
            case RANDOM:
                strategy = randomStrategy;
                break;
            default:
                throw new IllegalArgumentException("Invalid recommendation type");
        }
        return strategy.getRecommendations(input);
    }

    public enum RecommendationType {
        GENRE_BASED,
        AUTHOR_BASED,
        RANDOM
    }
}