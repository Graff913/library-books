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
public class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private StyleDao styleDao;

    @Test
    public void insert() {
        List<Book> books = bookDao.getAll();
        assertEquals(20, books.size());
        Book book = new Book("Тестовая книга");
        Author author = new Author("Тестовый автор");
        Style style = new Style("Тестовый стиль");
        book.setAuthors(Collections.singletonList(author));
        book.setStyles(Collections.singletonList(style));
        bookDao.insert(book);
        books = bookDao.getAll();
        assertEquals(21, books.size());
        Book testBook = bookDao.getByName("Тестовая книга");
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
        bookDao.insert(book);

        assertEquals(18, authorDao.getAll().size());

        Book book2 = new Book("Тестовая книга 2");
        book2.setAuthors(Collections.singletonList(author));
        book2.setStyles(Collections.singletonList(style));
        bookDao.insert(book2);

        assertEquals(18, authorDao.getAll().size());
    }

    @Test
    public void linkStyle() {
        Book book = new Book("Тестовая книга");
        Author author = new Author("Тестовый автор");
        Style style = new Style("Тестовый стиль");
        book.setAuthors(Collections.singletonList(author));
        book.setStyles(Collections.singletonList(style));
        bookDao.insert(book);

        assertEquals(21, styleDao.getAll().size());

        Book book2 = new Book("Тестовая книга 2");
        book2.setAuthors(Collections.singletonList(author));
        book2.setStyles(Collections.singletonList(style));
        bookDao.insert(book2);

        assertEquals(21, styleDao.getAll().size());
    }

    @Test
    public void getByName() {
        Book book = bookDao.getByName("Герои нашего времени");
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
        List<Book> books = bookDao.getAll();
        assertEquals(20, books.size());
    }

    @Test
    public void deleteById() {
        List<Book> books = bookDao.getAll();
        assertEquals(20, books.size());
        bookDao.deleteById(10);
        books = bookDao.getAll();
        assertEquals(19, books.size());
        Book book = bookDao.getByName("Сборник стихов");
        assertNull(book);
    }

}