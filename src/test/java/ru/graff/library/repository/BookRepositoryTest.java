package ru.graff.library.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataMongoTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

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
        mongoTemplate.save(book);

        List<Book> gotBooks = bookRepository.findAll();

        assertEquals(gotBooks.get(0).getName(), book.getName());

    }

}