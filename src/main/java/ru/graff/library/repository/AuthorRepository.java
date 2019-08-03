package ru.graff.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.graff.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByName(String name);

    List<Author> findAll();

}
