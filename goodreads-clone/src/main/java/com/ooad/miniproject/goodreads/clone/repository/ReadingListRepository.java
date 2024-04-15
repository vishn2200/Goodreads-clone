package com.ooad.miniproject.goodreads.clone.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ooad.miniproject.goodreads.clone.entity.ReadingStatus;
import com.ooad.miniproject.goodreads.clone.entity.ReadingListBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReadingListRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ReadingListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new JdbcTemplate();
    }

    
    public void addToReadingList(Long readingListId, Long bookId, String title, ReadingStatus status, Double rating) {
        String sql = "INSERT INTO reading_list_book (reading_list_id, book_id, title, status, rating) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, readingListId, bookId, title, status.toString(), rating);
    }

    private RowMapper<ReadingListBook> bookRowMapper = new RowMapper<ReadingListBook>() {
        @Override
        public ReadingListBook mapRow(ResultSet resultSet, int i) throws SQLException {
            ReadingListBook readingListBook = new ReadingListBook();
            //readingListBook.setId(resultSet.getLong("id"));
            readingListBook.setReadingListId(resultSet.getLong("reading_list_id"));
            readingListBook.setBookId(resultSet.getLong("book_id"));
            readingListBook.setTitle(resultSet.getString("title"));
            readingListBook.setStatus(ReadingStatus.valueOf(resultSet.getString("status")));
            readingListBook.setRating(resultSet.getDouble("rating"));
            return readingListBook;
        }
    };
    
    public List<ReadingListBook> getReadingListBooks(Long readingListId) {
        String sql = "SELECT * FROM reading_list_book WHERE reading_list_id = ?";
        return jdbcTemplate.query(sql, bookRowMapper, readingListId);
    }

    
    public void deleteFromReadingList(Long readingListId, String title) {
        String sql = "DELETE FROM reading_list_book WHERE reading_list_id = ? AND title = ?";
        jdbcTemplate.update(sql, readingListId, title);
    }

    
    public void setBookStatusAndRating(Long readingListId, Long bookId, ReadingStatus status, Double rating) {
        String sql = "UPDATE reading_list_book SET status = ?, rating = ? WHERE reading_list_id = ? AND book_id = ?";
        jdbcTemplate.update(sql, status.toString(), rating, readingListId, bookId);
    }

    // Method to get the reading list ID for the current user
    public Long getCurrentUserReadingListId(Long userId) {
        // Perform a database query to fetch the reading list ID associated with the user's username
        // Example query using JdbcTemplate:
        String sql = "SELECT reading_list_id FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }
}
