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
    private StyleRepository styleRepository;

    @Test
    public void insert() {
        assertEquals(20, styleRepository.getAll().size());
        Style style = new Style("Тестовый стиль");
        styleRepository.insert(style);
        assertEquals(21, styleRepository.getAll().size());
        Style testStyle = styleRepository.getByName("Тестовый стиль");
        assertEquals(21, testStyle.getId());
        assertEquals("Тестовый стиль", testStyle.getName());
    }

    @Test
    public void getByName() {
        Style style = styleRepository.getByName("Романтизм");
        assertEquals(14, style.getId());
        assertEquals("Романтизм", style.getName());
    }

    @Test
    public void getAll() {
        List<Style> styles = styleRepository.getAll();
        assertEquals(20, styles.size());
    }

    @Test
    public void deleteById() {
        List<Style> styles = styleRepository.getAll();
        assertEquals(20, styles.size());
        styleRepository.delete(styles.get(16));
        styles = styleRepository.getAll();
        assertEquals(19, styles.size());
        Style style = styleRepository.getByName("Сказки");
        assertNull(style);
    }

}