package ru.graff.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.graff.library.dao.LibraryService;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

@ShellComponent
public class LibraryCommands {

//    add-book --name-book name1 --name-author name2 --name-style name3
//    add-book --name-book name4 --name-author name5 --name-style name6

//    show-all-author
//    show-all-books
//    show-all-styles

    private final LibraryService service;

    @Autowired
    public LibraryCommands(LibraryService service) {
        this.service = service;
    }

    @ShellMethod("Show all books.")
    public String showAllBooks() {
        StringBuilder str = new StringBuilder();
        service.showAllBooks().forEach(b -> { str.append(b); str.append("\n"); });
        return str.toString();
    }

    @ShellMethod("Add book.")
    public void addBook(
            @ShellOption String nameBook,
            @ShellOption String nameAuthor,
            @ShellOption String nameStyle) {
        Book book = new Book(nameBook);
        Author author = new Author(nameAuthor);
        Style style = new Style(nameStyle);
        book.addAuthor(author);
        book.addStyle(style);
        service.addBook(book);
    }

    @ShellMethod("Show all authors.")
    public String showAllAuthor() {
        StringBuilder str = new StringBuilder();
        service.showAllAuthor().forEach(a -> { str.append(a); str.append("\n"); });
        return str.toString();
    }

    @ShellMethod("Show all styles.")
    public String showAllStyles() {
        StringBuilder str = new StringBuilder();
        service.showAllStyles().forEach(s -> { str.append(s); str.append("\n"); });
        return str.toString();
    }

}
