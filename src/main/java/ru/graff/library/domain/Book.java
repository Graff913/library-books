package ru.graff.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "books")
public class Book {

    @Id
    private int id;
    @Field("name")
    private String name;

    @DBRef
    private Style style;

    @DBRef
    private Author author;

    public Book() {
    }

    public Book(String name, Author author, Style style) {
        this.name = name;
        this.author = author;
        this.style = style;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Название книги: ");
        str.append(name);
        str.append(";\n     Автор: ");
        str.append(author);
        str.append(";\n     Cтиль книги: ");
        str.append(style);
        str.append(".");
        return str.toString();
    }
}
