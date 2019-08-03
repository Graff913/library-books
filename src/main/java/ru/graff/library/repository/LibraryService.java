package ru.graff.library.repository;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    List<Book> showAllBooks();

    Optional<Book> findBookById(Integer id);

    void addBook(String name, String author, String style);

    void updateBook(Integer id, String name, String authorName, String styleName);

    void deleteBook(Integer id);

    List<Author> showAllAuthor();

    List<Style> showAllStyles();

    long countBooks();

    long countAuthors();

    long countStyles();

}
