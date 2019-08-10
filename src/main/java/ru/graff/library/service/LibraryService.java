package ru.graff.library.service;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

public interface LibraryService {

    List<Book> showAllBooks();

    List<Book> showAllBooksByAuthorName(String author);

    void addBook(String name, String author, String style);

    List<Author> showAllAuthor();

    List<Style> showAllStyles();

}
