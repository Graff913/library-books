package ru.graff.library.dao;

import org.springframework.stereotype.Service;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

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
    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
        authorRepository.saveAll(book.getAuthors());
        styleRepository.saveAll(book.getStyles());
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
