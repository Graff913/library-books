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
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void insert() {
        assertEquals(17, authorDao.getAll().size());
        Author author = new Author("Тестовый автор");
        int id = authorDao.insert(author);
        assertEquals(18, id);
        assertEquals(18, authorDao.getAll().size());
        Author testAuthor = authorDao.getByName("Тестовый автор");
        assertEquals(18, testAuthor.getId());
        assertEquals("Тестовый автор", testAuthor.getName());
    }

    @Test
    public void getByName() {
        Author author = authorDao.getByName("Горький Максим");
        assertEquals(2, author.getId());
        assertEquals("Горький Максим", author.getName());
    }

    @Test
    public void getAll() {
        List<Author> authors = authorDao.getAll();
        assertEquals(17, authors.size());
    }

    @Test
    public void getAuthorsByBookId() {
        List<Author> authors = authorDao.getAuthorsByBookId(7);
        assertEquals(1, authors.size());
        assertEquals(17, authors.get(0).getId());
        assertEquals("Есенин Сергей Александрович", authors.get(0).getName());
    }

    @Test
    public void deleteById() {
        List<Author> authors = authorDao.getAll();
        assertEquals(17, authors.size());
        authorDao.deleteById(16);
        authors = authorDao.getAll();
        assertEquals(16, authors.size());
        Author author = authorDao.getByName("Чехов Антон Павлович");
        assertNull(author);
    }

}