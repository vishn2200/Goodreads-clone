package com.ooad.miniproject.goodreads.clone.repository;

import com.ooad.miniproject.goodreads.clone.entity.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO user(username, email, password) VALUES (?, ?, ?)";
        String sql1 = "DELETE FROM user where username is NULL";
        jdbcTemplate.update(sql1);
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword());
    }

    public boolean checkUser(String username, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
        
        // Create a PreparedStatementCreator to create a PreparedStatement
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            return ps;
        };
        
        // Execute the SQL query and use RowMapper to map the result
        List<Integer> counts = jdbcTemplate.query(preparedStatementCreator, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        });
        
        // Check if the returned list is not empty and if the count is greater than 0
        return !counts.isEmpty() && counts.get(0) > 0;
    }

    public void updateUser(User user) {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getUsername());
    }

    public void deleteUserByUsername(String username) {
        String sql = "DELETE FROM user WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    public Long getUserIdByUsername(String username) {
        String sql = "SELECT id FROM user WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, username);
    }

}
