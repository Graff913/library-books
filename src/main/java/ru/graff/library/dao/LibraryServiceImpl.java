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

    public LibraryServiceImpl(AuthorRepository authorRepository, StyleRepository styleRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.styleRepository = styleRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> showAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.insert(book);
    }

    @Override
    public List<Author> showAllAuthor() {
        return authorRepository.getAll();
    }

    @Override
    public List<Style> showAllStyles() {
        return styleRepository.getAll();
    }

}
