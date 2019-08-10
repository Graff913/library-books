package ru.graff.library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Book> findAllBooksByAuthorName(String name) {
        List<Author> authors = mongoTemplate.find(query(where("name").is(name)), Author.class);
        return authors.get(0).getBooks();
    }

    @Override
    public void addBook(Author author, Book book) {
        author.getBooks().add(book);
        mongoTemplate.updateFirst(query(where("name").is(author.getName())),
                update("books", author.getBooks()), Author.class);
    }

}
