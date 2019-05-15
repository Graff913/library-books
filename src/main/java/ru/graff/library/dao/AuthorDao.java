package ru.graff.library.dao;

import ru.graff.library.domain.Author;

import java.util.List;

public interface AuthorDao {

    int insert(Author author);

    Author getByName(String name);

    List<Author> getAll();

    List<Author> getAuthorsByBookId(int bookId);

    void deleteById(int id);

}
