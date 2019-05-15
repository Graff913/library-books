package ru.graff.library.dao;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

public interface BookDao {

    int insert(Book book);

    void linkAuthor(Book book, Author author);

    void linkStyle(Book book, Style style);

    Book getByName(String name);

    List<Book> getAll();

    void deleteById(int id);

}
