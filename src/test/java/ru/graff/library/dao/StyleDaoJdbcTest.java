package ru.graff.library.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graff.library.domain.Style;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StyleDaoJdbcTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void getByName() {
        entityManager.persist(new Style("Тестовый стиль"));
        Style style = styleRepository.getByName("Тестовый стиль");
        assertEquals("Тестовый стиль", style.getName());
    }

    @Test
    public void getByNameNull() {
        Style style = styleRepository.getByName("Тестовый стиль");
        assertNull(style);
    }

}