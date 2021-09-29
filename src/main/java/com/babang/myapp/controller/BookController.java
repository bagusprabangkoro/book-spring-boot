package com.babang.myapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class BookController {
    private final List<Book> bookList;
    private final Faker faker;

    public BookController() {
        this.faker = new Faker();
        this.bookList = new ArrayList<>();
        Book book1 = Book.builder()
                .id(UUID.randomUUID())
                .author("Babang")
                .publishedDate(new Date())
                .title("Book 1")
                .publisher("Airlangga")
                .build();
        this.bookList.add(book1);
    }

    @GetMapping("/api/v1/books")
    public List<Book> getAllBooks() {
        return this.bookList;
    }

    @GetMapping("/api/v1/books/{id}")
    public Book getBook(@PathVariable String id) {
        return this.bookList.stream()
                .filter(i -> i.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/api/v1/books")
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

    @PutMapping("/api/v1/books")
    public String updateBook(@RequestBody Book request) {
        Book currentBook = this.bookList.stream()
                .filter(i -> i.getId().equals(request.getId()))
                .findFirst()
                .orElse(null);
        if (currentBook == null) {
            return "NOT FOUND";
        }

        currentBook.setTitle(request.title);
        currentBook.setAuthor(request.author);
        currentBook.setPublisher(request.publisher);
        currentBook.setPublishedDate(request.publishedDate);
        return "OK";
    }

    @DeleteMapping("/api/v1/books/{id}")
    public String deleteBook(@PathVariable String id) {
        Book book = this.bookList.stream()
                .filter(i -> i.id.toString().equals(id))
                .findFirst()
                .orElse(null);
        if (book == null) {
            return "NOT FOUND";
        }
        this.bookList.remove(book);
        return "OK";
    }

    @Builder
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Book {
        UUID id;
        String title;
        String author;
        String publisher;
        Date publishedDate;
    }
}
