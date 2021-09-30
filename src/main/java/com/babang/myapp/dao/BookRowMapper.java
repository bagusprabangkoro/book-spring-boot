package com.babang.myapp.dao;

import com.babang.myapp.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(UUID.fromString(rs.getString("id")))
                .author(rs.getString("author"))
                .title(rs.getString("title"))
                .publisher(rs.getString("publisher"))
                .publishedDate(rs.getDate("publisheddate"))
                .build();
    }
}
