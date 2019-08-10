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
import ru.graff.library.domain.Style;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@DataMongoTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class StyleRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void whenGetByName_thenReturnGenre(){
        Style style = new Style("Тестовый стиль");
        mongoTemplate.save(style);

        Style gotStyle = styleRepository.findByName(style.getName());

        assertThat(gotStyle.getName())
                .isEqualTo(style.getName());
    }

}