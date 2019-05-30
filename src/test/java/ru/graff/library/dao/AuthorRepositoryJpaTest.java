package ru.graff.library.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graff.library.domain.Author;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void getByName() {
        entityManager.persist(new Author("Тестовый автор"));
        Author author = authorRepository.getByName("Тестовый автор");
        assertEquals("Тестовый автор", author.getName());
    }

    @Test
    public void getByNameNull() {
        Author author = authorRepository.getByName("Тестовый автор");
        assertNull(author);
    }

}