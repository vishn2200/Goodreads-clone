package com.ooad.miniproject.goodreads.clone.service;

//import com.ooad.miniproject.goodreads.clone.entity.ReadingList;
import com.ooad.miniproject.goodreads.clone.entity.ReadingStatus;
import com.ooad.miniproject.goodreads.clone.entity.ReadingListBook;
import com.ooad.miniproject.goodreads.clone.repository.ReadingListRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReadingListService {
    private final ReadingListRepository readingListRepository;

    public ReadingListService(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    public void addToReadingList(Long readingListId, Long bookId, String title, ReadingStatus status, Double rating) {
        readingListRepository.addToReadingList(readingListId, bookId, title, status, rating);
    }

    public List<ReadingListBook> getReadingListBooks(Long readingListId) {
        return readingListRepository.getReadingListBooks(readingListId);
    }

    public void deleteFromReadingList(Long readingListId, String title) {
        readingListRepository.deleteFromReadingList(readingListId, title);
    }

    public void setBookStatusAndRating(Long readingListId, Long bookId, ReadingStatus status, Double rating) {
        readingListRepository.setBookStatusAndRating(readingListId, bookId, status, rating);
    }

    public Long getCurrentUserReadingListId(Long userId) {
        return readingListRepository.getCurrentUserReadingListId(userId);
    }
}