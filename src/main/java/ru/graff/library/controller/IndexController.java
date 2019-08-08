package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.graff.library.service.LibraryService;

@Controller
public class IndexController {

    private final LibraryService libraryService;

    @Autowired
    public IndexController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("booksCount", libraryService.countBooks());
        model.addAttribute("authorsCount", libraryService.countAuthors());
        model.addAttribute("stylesCount", libraryService.countStyles());
        return "index";
    }
}
