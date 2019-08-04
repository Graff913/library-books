package ru.graff.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/preview-styles")
    public String testStylesPage(Model model){
        return "redirect:/styles-table";
    }

    @GetMapping("/preview-authors")
    public String testAuthorPage(Model model){
        return "redirect:/authors-table";
    }

}
