package com.babang.myapp.dao;

import com.babang.myapp.model.Book;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository("postgre")
public class PostgreBookDao implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Faker faker;

    public PostgreBookDao() {
        this.faker = new Faker();
    }

    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT id, title, author, publisher, publisheddate FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Book getBook(String id) {
        String sql = "SELECT id, title, author, publisher, publisheddate FROM books ";
        sql += "WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{UUID.fromString(id)}, new BookRowMapper());
    }

    @Override
    public String generateRandomBook() {
        com.github.javafaker.Book randomBook = this.faker.book();
        Date now = new Date();
        Book newBook = Book.builder()
                .id(UUID.randomUUID())
                .title(randomBook.title())
                .author(randomBook.author())
                .publisher(randomBook.publisher())
                .publishedDate(this.faker.date().past(now.getSeconds(), TimeUnit.SECONDS))
                .build();
        return "OK";
    }

    @Override
    public String updateBook(Book request) {
        return null;
    }

    @Override
    public String deleteBook(String id) {
        return null;
    }
}
