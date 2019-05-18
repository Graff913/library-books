package ru.graff.library.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest("spring.shell.interactive.enabled=false")
@Transactional
public class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void insertAndDelete() {
        List<Author> authors = authorRepository.getAll();
        assertEquals(0, authors.size());
        Author testAuthor = new Author("Тестовый автор");
        assertEquals(0, testAuthor.getId());
        authorRepository.insert(testAuthor);
        assertEquals(1, testAuthor.getId());

        authors = authorRepository.getAll();
        assertEquals(1, authors.size());
        Author maximAuthor = new Author("Горький Максим");
        authorRepository.insert(maximAuthor);
        authors = authorRepository.getAll();
        assertEquals(2, authors.size());

        authorRepository.delete(maximAuthor);
        authors = authorRepository.getAll();
        assertEquals(1, authors.size());
    }

    @Test
    public void insertDuplicate() {
        List<Author> authors = authorRepository.getAll();
        assertEquals(0, authors.size());
        Author testAuthor = new Author("Тестовый автор");
        authorRepository.insert(testAuthor);
        assertEquals(1, testAuthor.getId());

        Author duplicateAuthor = new Author("Тестовый автор");
        authorRepository.insert(duplicateAuthor);
        assertEquals(2, duplicateAuthor.getId());
    }

    @Test
    public void getByNameNull() {
        Author author = authorRepository.getByName("Горький Максим");
        assertNull(author);
    }

}