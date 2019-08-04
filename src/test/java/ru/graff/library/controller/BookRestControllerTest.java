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
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;
import ru.graff.library.service.LibraryService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Configuration
    @ComponentScan(basePackageClasses = {BookRestController.class})
    public static class TestConf {
    }

    private Book book;
    private Book updatedBook;

    @Before
    public void setUp() throws Exception {
        Author author = new Author("Лев Толстой");
        author.setId(1);
        Style style = new Style("роман-эпопея");
        style.setId(1);
        book = new Book("Война и мир", author, style);
        book.setId(1);
        style.setName("роман");
        updatedBook = new Book("Анна Каренина", author, style);
    }

    @Test
    public void addBook() throws Exception {


        when(libraryService.addBook("Война и мир","Лев Толстой", "роман-эпопея")).thenReturn(book);
        this.mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookName\":\"Война и мир\",\"authorName\":\"Лев Толстой\",\"style\":\"роман-эпопея\"}")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(book.getName()))
                .andDo(print())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void delBook() throws Exception {
        this.mockMvc.perform(delete("/api/books/{id}", book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\" : \"1\"}")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void updateBook() throws Exception {
        when(libraryService.updateBook(1,"Анна Каренина","Лев Толстой", "роман")).thenReturn(updatedBook);
        this.mockMvc.perform(put("/api/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookName\":\"Анна Каренина\",\"authorName\":\"Лев Толстой\",\"style\":\"роман\"}")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedBook.getName()))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}