package ru.graff.library.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest("spring.shell.interactive.enabled=false")
@Transactional
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void insert() {
        List<Book> books = bookRepository.getAll();
        assertEquals(20, books.size());
        Book book = new Book("Тестовая книга");
        Author author = new Author("Тестовый автор");
        Style style = new Style("Тестовый стиль");
        book.setAuthors(Collections.singletonList(author));
        book.setStyles(Collections.singletonList(style));
        bookRepository.insert(book);
        books = bookRepository.getAll();
        assertEquals(21, books.size());
        Book testBook = bookRepository.getByName("Тестовая книга");
        assertEquals("Тестовая книга", testBook.getName());
        assertEquals(1, testBook.getAuthors().size());
        assertEquals("Тестовый автор", testBook.getAuthors().get(0).getName());
        assertEquals(1, testBook.getStyles().size());
        assertEquals("Тестовый стиль", testBook.getStyles().get(0).getName());
    }

    @Test
    public void linkAuthor() {
        Book book = new Book("Тестовая книга");
        Author author = new Author("Тестовый автор");
        Style style = new Style("Тестовый стиль");
        book.setAuthors(Collections.singletonList(author));
        book.setStyles(Collections.singletonList(style));
        bookRepository.insert(book);

        assertEquals(18, authorRepository.getAll().size());

        Book book2 = new Book("Тестовая книга 2");
        book2.setAuthors(Collections.singletonList(author));
        book2.setStyles(Collections.singletonList(style));
        bookRepository.insert(book2);

        assertEquals(18, authorRepository.getAll().size());
    }

    @Test
    public void linkStyle() {
        Book book = new Book("Тестовая книга");
        Author author = new Author("Тестовый автор");
        Style style = new Style("Тестовый стиль");
        book.setAuthors(Collections.singletonList(author));
        book.setStyles(Collections.singletonList(style));
        bookRepository.insert(book);

        assertEquals(21, styleRepository.getAll().size());

        Book book2 = new Book("Тестовая книга 2");
        book2.setAuthors(Collections.singletonList(author));
        book2.setStyles(Collections.singletonList(style));
        bookRepository.insert(book2);

        assertEquals(21, styleRepository.getAll().size());
    }

    @Test
    public void getByName() {
        Book book = bookRepository.getByName("Герои нашего времени");
        assertEquals(9, book.getId());
        assertEquals("Герои нашего времени", book.getName());
        assertEquals(1, book.getAuthors().size());
        assertEquals("Лермонтов Михаил Юрьевич", book.getAuthors().get(0).getName());
        assertEquals(2, book.getStyles().size());
        assertEquals("Психологический реализм", book.getStyles().get(0).getName());
        assertEquals("Романтизм", book.getStyles().get(1).getName());
    }

    @Test
    public void getAll() {
        List<Book> books = bookRepository.getAll();
        assertEquals(20, books.size());
    }

    @Test
    public void deleteById() {
        List<Book> books = bookRepository.getAll();
        assertEquals(20, books.size());
        bookRepository.delete(books.get(10));
        books = bookRepository.getAll();
        assertEquals(19, books.size());
        Book book = bookRepository.getByName("Сборник стихов");
        assertNull(book);
    }

}