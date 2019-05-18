package ru.graff.library.dao;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

public interface LibraryService {

    List<Book> showAllBooks();

    void addBook(Book book);

    List<Author> showAllAuthor();

    List<Style> showAllStyles();

}
