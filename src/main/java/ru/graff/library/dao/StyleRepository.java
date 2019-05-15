package ru.graff.library.dao;

import ru.graff.library.domain.Style;

import java.util.List;

public interface StyleRepository {

    void insert(Style style);

    Style getByName(String name);

    List<Style> getAll();

    void delete(Style style);

}
