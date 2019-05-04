package ru.graff.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graff.library.dao.AuthorDao;

@RunWith(SpringRunner.class)
@SpringBootTest("spring.shell.interactive.enabled=false")
public class LibraryApplicationTests {

    @Test
    public void contextLoads() {
    }

}
