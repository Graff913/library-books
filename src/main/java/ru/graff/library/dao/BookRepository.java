package ru.graff.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.graff.library.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book save(Book book);

    Book getByName(String name);

    List<Book> findAll();

    void delete(Book book);

}
