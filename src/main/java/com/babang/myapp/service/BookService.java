package com.babang.myapp.service;

import com.babang.myapp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBook(String id);

    String generateRandomBook();

    String updateBook(Book request);

    String deleteBook(String id);
}
