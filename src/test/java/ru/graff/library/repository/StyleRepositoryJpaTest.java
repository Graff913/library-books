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
import ru.graff.library.domain.Style;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StyleRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void whenGetByName_thenReturnStyle(){
        Style style = new Style("Тестовый стиль");
        entityManager.persist(style);
        entityManager.flush();

        Style gotStyle = styleRepository.findByName(style.getName());

        assertThat(gotStyle.getName())
                .isEqualTo(style.getName());
    }

}