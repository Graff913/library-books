package ru.graff.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.graff.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {

    Author findByName(String name);

    List<Author> findAll();

}
