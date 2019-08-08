package ru.graff.library.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Style;
import ru.graff.library.service.LibraryService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StyleController.class)
public class StyleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @Configuration
    @ComponentScan(basePackageClasses = {AuthorController.class})
    public static class TestConf {
    }

    private Style style;
    private List<Style> styles;

    @Before
    public void setUp() throws Exception {
        Author author = new Author("Лев Толстой");
        author.setId(1);
        style = new Style("роман-эпопея");
        style.setId(1);
        styles = Arrays.asList(style);
    }

    @Test
    public void commentsPage() throws Exception {
        Mockito.when(libraryService.showAllStyles()).thenReturn(styles);
        mvc.perform(get("/styles"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(style.getName())))
                .andExpect(view().name("styles"));
    }
}