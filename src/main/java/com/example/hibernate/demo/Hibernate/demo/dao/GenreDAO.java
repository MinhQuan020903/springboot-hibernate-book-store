package com.example.hibernate.demo.Hibernate.demo.dao;

import com.example.hibernate.demo.Hibernate.demo.entity.Book;
import com.example.hibernate.demo.Hibernate.demo.entity.Genre;

import java.util.List;

public interface GenreDAO {
    public List<Genre> getAllGenres();
    public Genre getGenre(Long id);
    public Genre addGenre(Genre genre);
    public Genre updateGenre(Long id, Genre genre);
    public boolean deleteById(Long id);

}
