package ru.graff.library.repository;

import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;

import java.util.List;

public interface AuthorRepositoryCustom {

    List<Book> findAllBooksByAuthorName(String name);

    void addBook(Author author, Book book);

}
