package com.ooad.miniproject.goodreads.clone.repository;

import com.ooad.miniproject.goodreads.clone.entity.BookClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class BookClubRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookClubRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<BookClub> bookclubRowMapper = new RowMapper<BookClub>() {
        @Override
        public BookClub mapRow(ResultSet rs, int rowNum) throws SQLException {
            BookClub bookClub = new BookClub();
            bookClub.setId(rs.getLong("id"));
            bookClub.setName(rs.getString("name"));
            return bookClub;
        }
    };

    public void createBookClub(BookClub bookClub) {
        jdbcTemplate.update("INSERT INTO book_club (name) VALUES (?)", bookClub.getName());
    }

    public void joinBookClub(Long userId, Long clubId) {
        jdbcTemplate.update("INSERT INTO user_book_club (user_id, club_id) VALUES (?, ?)", userId, clubId);
    }

    public List<BookClub> viewBookClub() {
        String sql = "SELECT * FROM book_club";
        return jdbcTemplate.query(sql, bookclubRowMapper);
    }

    // Add more methods for joining book clubs, fetching specific book clubs, etc.
}
