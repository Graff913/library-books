package ru.graff.library.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LibraryServiceImpl implements LibraryService {

    private final AuthorRepository authorRepository;
    private final StyleRepository styleRepository;
    private final BookRepository bookRepository;

    public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, StyleRepository styleRepository) {
        this.authorRepository = authorRepository;
        this.styleRepository = styleRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> showAllBooksByAuthorName(String authorName) {
        return authorRepository.findAllBooksByAuthorName(authorName);
    }

    @Override
    public void addBook(String name, String authorName, String styleName) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) {
            author = new Author(authorName);
            authorRepository.save(author);
        }
        Style style = styleRepository.findByName(styleName);
        if (style == null) {
            style = new Style(styleName);
            styleRepository.save(style);
        }
        Book book = new Book(name, author, style);
        bookRepository.save(book);
        authorRepository.addBook(author, book);
    }

    @Override
    public List<Author> showAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public List<Style> showAllStyles() {
        return styleRepository.findAll();
    }

}
