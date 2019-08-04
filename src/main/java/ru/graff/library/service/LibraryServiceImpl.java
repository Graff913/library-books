package ru.graff.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;
import ru.graff.library.repository.AuthorRepository;
import ru.graff.library.repository.BookRepository;
import ru.graff.library.repository.StyleRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
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
    @Transactional(readOnly = true)
    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book addBook(String name, String authorName, String styleName) {
        Optional<Author> authorOptional = authorRepository.findByName(authorName);
        Author author;
        if (!authorOptional.isPresent()) {
            author = new Author(authorName);
            authorRepository.save(author);
        } else {
            author = authorOptional.get();
        }
        Style style = styleRepository.findByName(styleName);
        if (style == null) {
            style = new Style(styleName);
            styleRepository.save(style);
        }
        Book book = new Book(name, author, style);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Integer id, String name, String authorName, String styleName) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Author author;
            Optional<Author> authorOptional = authorRepository.findByName(authorName);
            if (!authorOptional.isPresent()){
                author = new Author(authorName);
                authorRepository.save(author);
            } else {
                author = authorOptional.get();
            }
            Style style;
            Optional<Style> styleOptional = Optional.ofNullable(styleRepository.findByName(styleName));
            if (!styleOptional.isPresent()){
                style = new Style(styleName);
                styleRepository.save(style);
            } else {
                style = styleOptional.get();
            }
            Book book = bookOptional.get();
            book.setName(name);
            book.setAuthor(author);
            book.setStyle(style);
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> showAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Style> showAllStyles() {
        return styleRepository.findAll();
    }

    @Override
    public void deleteStyle(Integer id) {
        styleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countBooks() {
        return bookRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countAuthors() {
        return authorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countStyles() {
        return styleRepository.count();
    }

}
