package ru.graff.library.dao;

import ru.graff.library.domain.Style;

import java.util.List;

public interface StyleDao {

    int insert(Style style);

    Style getByName(String name);

    List<Style> getAll();

    List<Style> getStylesByBookId(int bookId);

    void deleteById(int id);

}
