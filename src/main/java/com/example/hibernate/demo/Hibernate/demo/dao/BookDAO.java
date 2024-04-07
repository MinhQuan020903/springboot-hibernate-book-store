package com.example.hibernate.demo.Hibernate.demo.dao;

import com.example.hibernate.demo.Hibernate.demo.entity.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> getAllBooks();
    public Book getBook(Long id);
    public Book addBook(Book book);
    public Book updateBook(Long id, Book book);
    public void deleteById(Long id);

}
