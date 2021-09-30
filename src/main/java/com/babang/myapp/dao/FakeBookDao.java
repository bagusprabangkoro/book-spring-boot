package com.babang.myapp.dao;

import com.babang.myapp.model.Book;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository("fake")
public class FakeBookDao implements BookDao{
    private final List<Book> bookList;
    private final Faker faker;

    public FakeBookDao() {
        faker = new Faker();
        bookList = new ArrayList<>();
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookList;
    }

    @Override
    public Book getBook(String id) {
        return this.bookList.stream()
                .filter(i -> i.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
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
        this.bookList.add(newBook);
        return "OK";
    }

    @Override
    public String updateBook(Book request) {
        Book currentBook = this.bookList.stream()
                .filter(i -> i.getId().equals(request.getId()))
                .findFirst()
                .orElse(null);
        if (currentBook == null) {
            return "NOT FOUND";
        }

        currentBook.setTitle(request.getTitle());
        currentBook.setAuthor(request.getAuthor());
        currentBook.setPublisher(request.getPublisher());
        currentBook.setPublishedDate(request.getPublishedDate());
        return "OK";
    }

    @Override
    public String deleteBook(String id) {
        Book book = this.bookList.stream()
                .filter(i -> i.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
        if (book == null) {
            return "NOT FOUND";
        }
        this.bookList.remove(book);
        return "OK";
    }
}
