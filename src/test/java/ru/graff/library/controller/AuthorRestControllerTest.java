package ru.graff.library.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.graff.library.domain.Author;
import ru.graff.library.service.LibraryService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorRestController.class)
public class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Configuration
    @ComponentScan(basePackageClasses = {AuthorRestController.class})
    public static class TestConf {
    }

    private Author author;

    @Before
    public void setUp() throws Exception {
        author = new Author("Лев Толстой");
        author.setId(1);
    }

    @Test
    public void delAuthor() throws Exception {
        this.mockMvc.perform(delete("/api/authors/{id}", author.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\"}")
        )
                .andExpect(status().isOk());
    }

}