package ru.graff.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.graff.library.domain.Book;
import ru.graff.library.dto.BookDto;
import ru.graff.library.service.LibraryService;

@RestController
public class BookRestController {

    private final LibraryService libraryService;

    @Autowired
    public BookRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/api/books")
    public Book addBook(
            @RequestBody BookDto bookDto) {
        return libraryService.addBook(bookDto.getBookName(),  bookDto.getAuthorName(), bookDto.getStyle());
    }

    @DeleteMapping("/api/books/{id}")
    public Book delBook(
            @RequestBody BookDto bookDto) {
        libraryService.deleteBook(bookDto.getId());
        return new Book();
    }

    @PutMapping("/api/books/{id}")
    public Book updateBook(
            @RequestBody BookDto bookDto,
            @PathVariable("id") Integer id
    ) {
        return libraryService.updateBook(id, bookDto.getBookName(),
                bookDto.getAuthorName(), bookDto.getStyle());
    }

}
