package ru.graff.library.repository;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataMongoTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class AuthorRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void whenGetByName_thenReturnAuthor(){
        Author author = new Author("Тестовый автор");
        mongoTemplate.save(author);

        Author gotAuthor = authorRepository.findByName(author.getName());

        assertThat(gotAuthor.getName())
                .isEqualTo(author.getName());
    }

    @Test
    public void whenGetByAuthorLastName_thenReturnBook(){
        Author author = new Author("Тестовый автор");
        authorRepository.save(author);
        Style style = new Style("Тестовый стиль");
        styleRepository.save(style);
        Book book = new Book("Тестовая книга", author, style);
        bookRepository.save(book);
        author.getBooks().add(book);
        mongoTemplate.updateFirst(query(where("id").is(author.getId())), update("books", author.getBooks()), Author.class);

        List<Book> gotBooksByAuthorLastName = authorRepository.findAllBooksByAuthorName(author.getName());

        assertThat(gotBooksByAuthorLastName.get(0).getName())
                .isEqualTo(book.getName());
    }

}