package ru.graff.library.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Style;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest("spring.shell.interactive.enabled=false")
@Transactional
public class StyleDaoJdbcTest {

    @Autowired
    private StyleDao styleDao;

    @Test
    public void insert() {
        assertEquals(20, styleDao.getAll().size());
        Style style = new Style("Тестовый стиль");
        int id = styleDao.insert(style);
        assertEquals(21, id);
        assertEquals(21, styleDao.getAll().size());
        Style testStyle = styleDao.getByName("Тестовый стиль");
        assertEquals(21, testStyle.getId());
        assertEquals("Тестовый стиль", testStyle.getName());
    }

    @Test
    public void getByName() {
        Style style = styleDao.getByName("Романтизм");
        assertEquals(14, style.getId());
        assertEquals("Романтизм", style.getName());
    }

    @Test
    public void getAll() {
        List<Style> styles = styleDao.getAll();
        assertEquals(20, styles.size());
    }

    @Test
    public void getStylesByBookId() {
        List<Style> styles = styleDao.getStylesByBookId(1);
        assertEquals(6, styles.size());
    }

    @Test
    public void deleteById() {
        List<Style> styles = styleDao.getAll();
        assertEquals(20, styles.size());
        styleDao.deleteById(16);
        styles = styleDao.getAll();
        assertEquals(19, styles.size());
        Style style = styleDao.getByName("Сказки");
        assertNull(style);
    }

}