package ru.graff.library.dao;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

public interface BookRepository {

    void insert(Book book);

    Book getByName(String name);

    List<Book> getAll();

    void delete(Book book);

}
