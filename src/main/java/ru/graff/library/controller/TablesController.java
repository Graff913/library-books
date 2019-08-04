package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;
import ru.graff.library.service.LibraryService;

import java.util.List;
import java.util.Optional;

@Controller
public class TablesController {

    private final LibraryService libraryService;

    @Autowired
    public TablesController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books-table")
    public String booksTablePage(Model model) {
        List<Book> books = libraryService.showAllBooks();
        model.addAttribute("books", books);
        return "books-table";
    }

    @GetMapping("/authors-table")
    public String authorsTablePage(Model model) {
        List<Author> authors = libraryService.showAllAuthor();
        model.addAttribute("authors", authors);
        return "authors-table";
    }

    @GetMapping("/styles-table")
    public String stylesTablePage(Model model) {
        List<Style> styleList = libraryService.showAllStyles();
        model.addAttribute("styles", styleList);
        return "styles-table";
    }

    @GetMapping("/editor/{id}")
    public String booksEditorPage(
            @PathVariable("id") Integer id,
            Model model
    ) {
        Optional<Book> book = libraryService.findBookById(id);
        model.addAttribute("book", book.get());
        return "editor";
    }

    @GetMapping("/blankeditor")
    public String blankPage(Model model) {
        return "editor-blank";
    }
}
