package ru.graff.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.graff.library.dao.LibraryService;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.Collections;

@ShellComponent
public class LibraryCommands {

    private final LibraryService service;

    @Autowired
    public LibraryCommands(LibraryService service) {
        this.service = service;
    }

    @ShellMethod("Show all books.")
    public String showAllBooks() {
        return service.showAllBooks();
    }

    @ShellMethod("Add book.")
    public void addBook(
            @ShellOption String nameBook,
            @ShellOption String nameAuthor,
            @ShellOption String nameStyle) {
        Book book = new Book(nameBook);
        Author author = new Author(nameAuthor);
        Style style = new Style(nameStyle);
        book.setAuthors(Collections.singletonList(author));
        book.setStyles(Collections.singletonList(style));
        service.addBook(book);
    }

    @ShellMethod("Add author book.")
    public void addAuthorBook(
            @ShellOption String nameBook,
            @ShellOption String nameAuthor) {
        Book book = new Book(nameBook);
        Author author = new Author(nameAuthor);
        service.addAuthorBook(book, author);
    }

    @ShellMethod("Add style book.")
    public void addStyleBook(
            @ShellOption String nameBook,
            @ShellOption String nameStyle) {
        Book book = new Book(nameBook);
        Style style = new Style(nameStyle);
        service.addStyleBook(book, style);
    }

    @ShellMethod("Show all authors.")
    public String showAllAuthor() {
        return service.showAllAuthor();
    }

    @ShellMethod("Show all styles.")
    public String showAllStyles() {
        return service.showAllBooks();
    }


}
