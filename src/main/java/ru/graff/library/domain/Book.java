package ru.graff.library.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany
    private List<Style> styles;
    @OneToMany
    private List<Author> authors;

    public Book() {
    }

    public Book(String name) {
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

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Название книги: ");
        str.append(name);
        str.append(";\n     Авторы: ");
        if (authors.size() > 0) {
            str.append(authors.get(0));
        } else {
            str.append("нет авторов");
        }
        for (int i = 1; i < authors.size(); i++) {
            str.append(", ").append(authors.get(i));
        }
        str.append(";\n     Cтили книги: ");
        if (styles.size() > 0) {
            str.append(styles.get(0));
        } else {
            str.append("нет стилей");
        }
        for (int i = 1; i < styles.size(); i++) {
            str.append(", ").append(styles.get(i));
        }
        str.append(".");
        return str.toString();
    }
}
