package ru.graff.library.dto;

import ru.graff.library.domain.Book;

public class BookDto {

    private Integer id;
    private String bookName;
    private String authorName;
    private String style;

    public BookDto() {
    }

    public BookDto(Integer id, String bookName, String authorName, String style) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.style = style;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public static BookDto toDto(Book book){
        return new BookDto(book.getId(), book.getName(), book.getAuthor().getName(), book.getStyle().getName());
    }

}
