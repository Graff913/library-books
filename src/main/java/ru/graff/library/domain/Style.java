package ru.graff.library.domain;

public class Style {

    private int id;
    private final String name;

    public Style(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Style(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
