package com.ooad.miniproject.goodreads.clone.repository;

import com.ooad.miniproject.goodreads.clone.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Book> bookRowMapper = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setGenre(resultSet.getString("genre"));
            book.setRating(resultSet.getDouble("rating"));
            book.setImagePath(resultSet.getString("image_path"));
            book.setSummary(resultSet.getString("summary"));
            return book;
        }
    };

    public List<Book> searchBooks(String query) {
        String sql = "SELECT * FROM books WHERE name LIKE '%" + query + "%' OR author LIKE '%" + query + "%'";

        return jdbcTemplate.query(sql, bookRowMapper);
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, bookRowMapper);
    }
    
    // Other CRUD operations
}
