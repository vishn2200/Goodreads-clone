package com.ooad.miniproject.goodreads.clone.service;

import com.ooad.miniproject.goodreads.clone.entity.BookClub;
import com.ooad.miniproject.goodreads.clone.repository.BookClubRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookClubService {

    private final BookClubRepository bookClubRepository;

    public BookClubService(BookClubRepository bookClubRepository) {
        this.bookClubRepository = bookClubRepository;
    }

    public void createBookClub(BookClub bookClub) {
        bookClubRepository.createBookClub(bookClub);
    }

    public void joinBookClub(Long userId, Long clubId) {
        bookClubRepository.joinBookClub(userId, clubId);
    }

    public List<BookClub> viewBookClub() {
        return bookClubRepository.viewBookClub();
    }

    // Add more methods for joining book clubs, fetching specific book clubs, etc.
}
