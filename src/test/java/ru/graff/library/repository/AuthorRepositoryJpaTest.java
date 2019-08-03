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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenGetByName_thenReturnAuthor(){
        Author author = new Author("Тестовый автор");
        entityManager.persist(author);
        entityManager.flush();

        Optional<Author> gotAuthor = authorRepository.findByName(author.getName());

        assertThat(gotAuthor.get().getName())
                .isEqualTo(author.getName());
    }

}