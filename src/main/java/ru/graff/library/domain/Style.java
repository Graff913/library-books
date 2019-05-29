package ru.graff.library.domain;

import javax.persistence.*;

@Entity
public class Style {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne
    private Book book;

    public Style() {
    }

    public Style(String name) {
        this.name = name;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return name;
    }

}
