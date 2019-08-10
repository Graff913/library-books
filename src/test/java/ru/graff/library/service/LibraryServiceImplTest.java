package ru.graff.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@SpringBootTest("spring.shell.interactive.enabled=false")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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

        doReturn(1L).when(authorRepository).count();
        doReturn(1L).when(styleRepository).count();
        doReturn(1L).when(bookRepository).count();
        doReturn(Collections.singletonList(author)).when(authorRepository).findAll();
        doReturn(Collections.singletonList(style)).when(styleRepository).findAll();
        doReturn(Collections.singletonList(book)).when(bookRepository).findAll();

        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);

        assertEquals(1, libraryService.countBooks());
        assertEquals(book.getName(), libraryService.showAllBooks().get(0).getName());
        assertEquals(1, libraryService.countAuthors());
        assertEquals(author.getName(), libraryService.showAllAuthor().get(0).getName());
        assertEquals(1, libraryService.countStyles());
        assertEquals(style.getName(), libraryService.showAllStyles().get(0).getName());
    }

    @Test
    public void workWithBook() {
        Optional<Author> mockAuthor = Optional.of(new Author("Тестовый автор"));
        Style mockStyle = new Style("Тестовый стиль");
        Book mockBook = new Book("Тестовая книг", mockAuthor.get(), mockStyle);

        doReturn(mockAuthor).when(authorRepository).findByName("Тестовый автор");
        doReturn(mockStyle).when(styleRepository).findByName("Тестовый стиль");
        doReturn(Collections.singletonList(mockBook)).when(bookRepository).findAll();

        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);
        libraryService.addBook("Тестовая книг", "Тестовый автор", "Тестовый стиль");

        Book book = libraryService.showAllBooks().get(0);
        assertEquals("Тестовая книг", book.getName());
        assertEquals("Тестовый автор", book.getAuthor().getName());
        assertEquals("Тестовый стиль", book.getStyle().getName());

        doReturn(Optional.of(mockBook)).when(bookRepository).findById(0);

        libraryService.updateBook(book.getId(), "Тестовая книг 2" , "Тестовый автор 2", "Тестовый стиль 2");

        Optional<Book> updateBook = libraryService.findBookById(book.getId());
        assertEquals("Тестовая книг 2", updateBook.get().getName());
        assertEquals("Тестовый автор 2", updateBook.get().getAuthor().getName());
        assertEquals("Тестовый стиль 2", updateBook.get().getStyle().getName());
    }

}