package ru.graff.library.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenGetById_thenReturnBook() {
        Author author = new Author("Тестовый автор");
        authorRepository.save(author);
        Style style = new Style("Тестовый стиль");
        styleRepository.save(style);

        Book book = new Book("Тестовая книг", author, style);
        entityManager.persist(book);
        entityManager.flush();

        Optional<Book> optionalBook = bookRepository.findById(1);
        assertEquals(optionalBook.get().getName(), book.getName());

    }

}