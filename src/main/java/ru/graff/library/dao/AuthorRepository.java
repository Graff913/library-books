package ru.graff.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.graff.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author getByName(String name);

    List<Author> findAll();

    void delete(Author author);

}
