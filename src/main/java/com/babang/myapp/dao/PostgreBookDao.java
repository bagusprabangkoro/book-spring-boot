package com.babang.myapp.dao;

import com.babang.myapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgre")
public class PostgreBookDao implements BookDao {
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getBook(String id) {
        return null;
    }

    @Override
    public String generateRandomBook() {
        return null;
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
