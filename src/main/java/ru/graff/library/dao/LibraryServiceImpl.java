package ru.graff.library.dao;

import org.springframework.stereotype.Service;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;
    private final StyleDao styleDao;
    private final BookDao bookDao;

    public LibraryServiceImpl(AuthorDao authorDao, StyleDao styleDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.styleDao = styleDao;
        this.bookDao = bookDao;
    }

    @Override
    public String showAllBooks() {
        List<Book> books = bookDao.getAll();
        StringBuilder str = new StringBuilder();
        books.forEach(b -> { str.append(b); str.append("\n"); });
        return str.toString();
    }

    @Override
    public void addBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void addAuthorBook(Book book, Author author) {
        bookDao.linkAuthor(book, author);
    }

    @Override
    public void addStyleBook(Book book, Style style) {
        bookDao.linkStyle(book, style);
    }

    @Override
    public String showAllAuthor() {
        List<Author> authors = authorDao.getAll();
        StringBuilder str = new StringBuilder();
        authors.forEach(a -> { str.append(a); str.append("\n"); });
        return str.toString();
    }

    @Override
    public String showAllStyles() {
        List<Style> styles = styleDao.getAll();
        StringBuilder str = new StringBuilder();
        styles.forEach(s -> { str.append(s); str.append("\n"); });
        return str.toString();
    }

}
