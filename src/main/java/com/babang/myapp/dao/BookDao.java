package com.babang.myapp.dao;

import com.babang.myapp.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();

    Book getBook(String id);

    String generateRandomBook();

    String updateBook(Book request);

    String deleteBook(String id);
}
