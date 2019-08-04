package ru.graff.library.service;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    List<Book> showAllBooks();

    Optional<Book> findBookById(Integer id);

    Book addBook(String name, String author, String style);

    Book updateBook(Integer id, String name, String authorName, String styleName);

    void deleteBook(Integer id);

    List<Author> showAllAuthor();

    void deleteAuthor(Integer id);

    List<Style> showAllStyles();


    void deleteStyle(Integer id);

    long countBooks();

    long countAuthors();

    long countStyles();

}
