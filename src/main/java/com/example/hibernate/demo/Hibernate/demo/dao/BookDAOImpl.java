package com.example.hibernate.demo.Hibernate.demo.dao;

import com.example.hibernate.demo.Hibernate.demo.entity.Book;
import com.example.hibernate.demo.Hibernate.demo.entity.Genre;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Setter
@Repository
public class BookDAOImpl implements BookDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Book> getAllBooks() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> bookList = session.createQuery("from books", Book.class).list();
        session.getTransaction().commit();
        session.close();
        return bookList;
    }

    @Override
    public Book getBook(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    @Override
    public Book addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Book> bookList = session.createQuery("from books", Book.class).list();
        for (Book b : bookList) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
                session.getTransaction().rollback();
                session.close();
                return null;
            }
        }
        try {
            session.persist(book);
            session.getTransaction().commit();
            session.close();
            return book;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    @Override
    public Book updateBook(Long id, Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Book> bookList = session.createQuery("from books", Book.class).list();
        for (Book b : bookList) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle()) && !Objects.equals(b.getId(), id)){
                session.getTransaction().rollback();
                session.close();
                return null;
            }
        }
        Book updatedBook = session.get(Book.class, id);

        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setGenre(book.getGenre());

        session.merge(updatedBook);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    @Override
    public void deleteById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            session.remove(book);
        }
        session.getTransaction().commit();
        session.close();
    }
}
