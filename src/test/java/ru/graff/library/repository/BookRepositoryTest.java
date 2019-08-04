package ru.graff.library.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Book book;

    @Before
    public void setUp() throws Exception {
        Author author = new Author("Лев Толстой");
        authorRepository.save(author);
        Style style = new Style("роман-эпопея");
        styleRepository.save(style);
        book = new Book("Война и мир", author, style);
        bookRepository.save(book);
    }

    @Test
    public void findAll() {
        List<Book> books = bookRepository.findAll();
        assertNotNull(books);
        assertEquals(book.getName(), books.get(0).getName());
    }

    @Test
    public void findById() {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        assertEquals(optionalBook.get().getName(), book.getName());
    }

    @Test
    public void deleteById() {
        bookRepository.deleteById(book.getId());
        assertEquals(0, bookRepository.count());
    }

}