package com.babang.myapp.service;

import com.babang.myapp.dao.BookDao;
import com.babang.myapp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    @Qualifier("postgre")
    BookDao bookDao;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDao.getAllBooks();
    }

    @Override
    public Book getBook(String id) {
        return this.bookDao.getBook(id);
    }

    @Override
    public String generateRandomBook() {
        return this.bookDao.generateRandomBook();
    }

    @Override
    public String updateBook(Book request) {
        return this.bookDao.updateBook(request);
    }

    @Override
    public String deleteBook(String id) {
        return this.bookDao.deleteBook(id);
    }
}
