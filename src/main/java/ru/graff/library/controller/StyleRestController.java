package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.graff.library.domain.Style;
import ru.graff.library.service.LibraryService;

@RestController
public class StyleRestController {

    private final LibraryService libraryService;

    @Autowired
    public StyleRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @DeleteMapping("/api/styles/{id}")
    public Style deleteStyle(
            @PathVariable("id") Integer id
    ) {
        libraryService.deleteStyle(id);
        return new Style();
    }

}
