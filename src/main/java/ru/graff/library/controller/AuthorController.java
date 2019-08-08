package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.graff.library.domain.Author;
import ru.graff.library.service.LibraryService;

import java.util.List;

@Controller
public class AuthorController {

    private final LibraryService libraryService;

    @Autowired
    public AuthorController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/authors")
    public String authorsPage(Model model) {
        List<Author> authors = libraryService.showAllAuthor();
        model.addAttribute("authors", authors);
        return "authors";
    }

}
