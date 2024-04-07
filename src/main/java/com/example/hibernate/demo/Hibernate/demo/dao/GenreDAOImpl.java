package com.example.hibernate.demo.Hibernate.demo.dao;

import com.example.hibernate.demo.Hibernate.demo.entity.Book;
import com.example.hibernate.demo.Hibernate.demo.entity.Genre;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Setter
@Repository
public class GenreDAOImpl implements GenreDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public GenreDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Genre> getAllGenres() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Genre> genreList = session.createQuery("from genres", Genre.class).list();
        session.getTransaction().commit();
        session.close();
        return genreList;
    }

    @Override
    public Genre getGenre(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Genre genre = session.get(Genre.class, id);
        session.getTransaction().commit();
        session.close();
        return genre;
    }

    @Override
    public Genre addGenre(Genre genre) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Genre> genreList = session.createQuery("from genres", Genre.class).list();
        for (Genre g : genreList) {
            if (g.getGenre().equalsIgnoreCase(genre.getGenre())) {
                session.getTransaction().rollback();
                session.close();
                return null;
            }
        }
        session.merge(genre);
        session.getTransaction().commit();
        session.close();
        return genre;
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Genre> genreList = session.createQuery("from genres", Genre.class).list();
        for (Genre g : genreList) {
            if (g.getGenre().equalsIgnoreCase(genre.getGenre())) {
                session.getTransaction().rollback();
                session.close();
                return null;
            }
        }
        Genre updatedGenre = session.get(Genre.class, id);
        updatedGenre.setGenre(genre.getGenre());
        session.persist(updatedGenre);
        session.getTransaction().commit();
        session.close();
        return updatedGenre;
    }

    @Override
    public void deleteById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Genre genre = session.get(Genre.class, id);
        if(genre != null){
            session.remove(genre);
        }
        session.getTransaction().commit();
        session.close();
    }
}
