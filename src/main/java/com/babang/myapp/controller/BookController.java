package com.babang.myapp.controller;

import com.babang.myapp.model.Book;
import com.babang.myapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/v1/books")
    public List<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("/api/v1/books/{id}")
    public Book getBook(@PathVariable String id) {
        return this.bookService.getBook(id);
    }

    @PostMapping("/api/v1/books")
    public String generateRandomBook() {
        return this.bookService.generateRandomBook();
    }

    @PutMapping("/api/v1/books")
    public String updateBook(@RequestBody Book request) {
        return this.bookService.updateBook(request);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public String deleteBook(@PathVariable String id) {
        return this.bookService.deleteBook(id);
    }
}
