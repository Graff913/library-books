package ru.graff.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.graff.library.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();

}
