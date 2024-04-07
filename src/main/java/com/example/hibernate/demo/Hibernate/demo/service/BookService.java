package com.example.hibernate.demo.Hibernate.demo.service;

import com.example.hibernate.demo.Hibernate.demo.dao.BookDAO;
import com.example.hibernate.demo.Hibernate.demo.dao.BookDAOImpl;
import com.example.hibernate.demo.Hibernate.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBook(Long id) {
        return bookDAO.getBook(id);
    }

    public Book addBook(Book book) {
        return bookDAO.addBook(book);
    }

    public Book updateBook(Long id, Book book) {
        return bookDAO.updateBook(id, book);
    }

    public void deleteById(Long id) {
        bookDAO.deleteById(id);
    }

}
