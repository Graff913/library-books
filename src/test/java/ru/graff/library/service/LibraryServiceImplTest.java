package ru.graff.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;
import ru.graff.library.repository.AuthorRepository;
import ru.graff.library.repository.BookRepository;
import ru.graff.library.repository.StyleRepository;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataMongoTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class LibraryServiceImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void showAll() {
        Author author = new Author("Тестовый автор");
        authorRepository.save(author);
        Style style = new Style("Тестовый стиль");
        styleRepository.save(style);
        Book book = new Book("Тестовая книг", author, style);
        bookRepository.save(book);

        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);
        assertEquals(libraryService.showAllBooks().get(0).getName(), book.getName());
        assertEquals(libraryService.showAllAuthor().get(0).getName(), author.getName());
        assertEquals(libraryService.showAllStyles().get(0).getName(), style.getName());
    }

    @Test
    public void addBook() {
        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);
        libraryService.addBook("Тестовая книг", "Тестовый автор", "Тестовый стиль");
        Book book = libraryService.showAllBooks().get(0);
        assertEquals("Тестовая книг", book.getName());
        assertEquals("Тестовый автор", book.getAuthor().getName());
        assertEquals("Тестовый стиль", book.getStyle().getName());

        Book bookByAuthor = libraryService.showAllBooksByAuthorName("Тестовый автор").get(0);
        assertEquals("Тестовая книг", bookByAuthor.getName());
        assertEquals("Тестовый автор", bookByAuthor.getAuthor().getName());
        assertEquals("Тестовый стиль", bookByAuthor.getStyle().getName());
    }

}