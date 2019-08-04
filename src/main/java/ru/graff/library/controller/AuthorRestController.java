package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.graff.library.domain.Author;
import ru.graff.library.service.LibraryService;

@RestController
public class AuthorRestController {

    private final LibraryService libraryService;

    @Autowired
    public AuthorRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @DeleteMapping("/api/authors/{id}")
    public Author deleteAuthor(
            @PathVariable("id") Integer id) {
        libraryService.deleteAuthor(id);
        Author author = new Author();
        return author;
    }

}
