package ru.graff.library.dao;

import ru.graff.library.domain.Author;

import java.util.List;

public interface AuthorRepository {

    void insert(Author author);

    Author getByName(String name);

    List<Author> getAll();

    void delete(Author author);

}
