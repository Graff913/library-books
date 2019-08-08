package ru.graff.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;
import ru.graff.library.repository.AuthorRepository;
import ru.graff.library.repository.BookRepository;
import ru.graff.library.repository.StyleRepository;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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

        assertEquals(1, libraryService.countBooks());
        assertEquals(book.getName(), libraryService.showAllBooks().get(0).getName());
        assertEquals(1, libraryService.countAuthors());
        assertEquals(author.getName(), libraryService.showAllAuthor().get(0).getName());
        assertEquals(1, libraryService.countStyles());
        assertEquals(style.getName(), libraryService.showAllStyles().get(0).getName());
    }

    @Test
    public void workWithBook() {
        LibraryService libraryService = new LibraryServiceImpl(bookRepository, authorRepository, styleRepository);
        libraryService.addBook("Тестовая книг", "Тестовый автор", "Тестовый стиль");
        Book book = libraryService.showAllBooks().get(0);
        assertEquals("Тестовая книг", book.getName());
        assertEquals("Тестовый автор", book.getAuthor().getName());
        assertEquals("Тестовый стиль", book.getStyle().getName());

        libraryService.updateBook(book.getId(), "Тестовая книг 2" , "Тестовый автор 2", "Тестовый стиль 2");

        Optional<Book> updateBook = libraryService.findBookById(book.getId());
        assertEquals("Тестовая книг 2", updateBook.get().getName());
        assertEquals("Тестовый автор 2", updateBook.get().getAuthor().getName());
        assertEquals("Тестовый стиль 2", updateBook.get().getStyle().getName());


    }

}