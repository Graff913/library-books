package ru.graff.library.dao;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

public interface LibraryService {

    String showAllBooks();

    void addBook(Book book);

    void addAuthorBook(Book book, Author author);

    void addStyleBook(Book book, Style style);

    String showAllAuthor();

    String showAllStyles();

}
