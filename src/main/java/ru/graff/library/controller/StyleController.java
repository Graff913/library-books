package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.graff.library.domain.Style;
import ru.graff.library.repository.LibraryService;

import java.util.List;

@Controller
public class StyleController {

    private final LibraryService libraryService;

    @Autowired
    public StyleController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/styles")
    public String genresPage(Model model) {
        List<Style> styleList = libraryService.showAllStyles();
        model.addAttribute("styles", styleList);
        return "styles";
    }

}
