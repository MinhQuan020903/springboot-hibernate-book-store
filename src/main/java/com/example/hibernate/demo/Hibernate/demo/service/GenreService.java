package com.example.hibernate.demo.Hibernate.demo.service;

import com.example.hibernate.demo.Hibernate.demo.dao.GenreDAO;
import com.example.hibernate.demo.Hibernate.demo.entity.Book;
import com.example.hibernate.demo.Hibernate.demo.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreDAO genreDAO;

    public List<Genre> getAllGenres() {
        return genreDAO.getAllGenres();
    }

    public Genre getGenre(Long id) {
        return genreDAO.getGenre(id);
    }

    public Genre addGenre(Genre genre) {
        return genreDAO.addGenre(genre);
    }

    public Genre updateGenre(Long id, Genre genre) {
        return genreDAO.updateGenre(id, genre);
    }

    public boolean deleteById(Long id) {
        return genreDAO.deleteById(id);
    }
}