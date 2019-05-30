package ru.graff.library.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save() {
        Book book = new Book("Тестовая книга");
        book.addAuthor(new Author("Тестовый автор"));
        book.addStyle(new Style("Тестовый стиль"));
        bookRepository.save(book);

        assertEquals(1, bookRepository.findAll().size());
        Book testBook = bookRepository.getByName("Тестовая книга");
        assertEquals("Тестовая книга", testBook.getName());
        assertEquals(1, testBook.getAuthors().size());
        assertEquals("Тестовый автор", testBook.getAuthors().get(0).getName());
        assertEquals(1, testBook.getStyles().size());
        assertEquals("Тестовый стиль", testBook.getStyles().get(0).getName());
    }

    @Test
    public void getByName() {
        entityManager.persist(new Book("Тестовая книга"));
        Book book = bookRepository.getByName("Тестовая книга");
        assertEquals("Тестовая книга", book.getName());
    }

    @Test
    public void getByNameNull() {
        Book book = bookRepository.getByName("Тестовая книга");
        assertNull(book);
    }

}