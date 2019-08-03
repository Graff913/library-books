package ru.graff.library.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;

    @ManyToOne
    @JoinColumn(name = "author_id")
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
