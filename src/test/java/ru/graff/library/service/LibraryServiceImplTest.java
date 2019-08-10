package ru.graff.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;
import ru.graff.library.repository.AuthorRepository;
import ru.graff.library.repository.BookRepository;
import ru.graff.library.repository.StyleRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataMongoTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class LibraryServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private StyleRepository styleRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void showAll() {
        Author author = new Author("Тестовый автор");
        Style style = new Style("Тестовый стиль");
        Book book = new Book("Тестовая книг", author, style);

        doReturn(Collections.singletonList(author)).when(authorRepository).findAll();
        doReturn(Collections.singletonList(style)).when(styleRepository).findAll();
        doReturn(Collections.singletonList(book)).when(bookRepository).findAll();

        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);

        assertEquals(book.getName(), libraryService.showAllBooks().get(0).getName());
        assertEquals(author.getName(), libraryService.showAllAuthor().get(0).getName());
        assertEquals(style.getName(), libraryService.showAllStyles().get(0).getName());
    }

    @Test
    public void workWithBook() {
        Author mockAuthor = new Author("Тестовый автор");
        Style mockStyle = new Style("Тестовый стиль");
        Book mockBook = new Book("Тестовая книг", mockAuthor, mockStyle);

        doReturn(Collections.singletonList(mockBook)).when(bookRepository).findAll();

        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);
        libraryService.addBook("Тестовая книг", "Тестовый автор", "Тестовый стиль");

        Book book = libraryService.showAllBooks().get(0);
        assertEquals("Тестовая книг", book.getName());
        assertEquals("Тестовый автор", book.getAuthor().getName());
        assertEquals("Тестовый стиль", book.getStyle().getName());

        doReturn(Collections.singletonList(mockBook)).when(authorRepository).findAllBooksByAuthorName("Тестовый автор");
        Book bookByAuthor = libraryService.showAllBooksByAuthorName("Тестовый автор").get(0);
        assertEquals("Тестовая книг", bookByAuthor.getName());
        assertEquals("Тестовый автор", bookByAuthor.getAuthor().getName());
        assertEquals("Тестовый стиль", bookByAuthor.getStyle().getName());
    }

}