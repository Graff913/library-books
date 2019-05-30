package ru.graff.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.graff.library.domain.Style;

import java.util.List;

public interface StyleRepository extends CrudRepository<Style, Integer> {

    Style getByName(String name);

    List<Style> findAll();

    void delete(Style style);

}
