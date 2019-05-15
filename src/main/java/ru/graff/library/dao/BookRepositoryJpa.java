package ru.graff.library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("JpaQlInspection")
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    private final AuthorRepository authorRepository;
    private final StyleRepository styleRepository;

    public BookRepositoryJpa(AuthorRepository authorRepository, StyleRepository styleRepository) {
        this.authorRepository = authorRepository;
        this.styleRepository = styleRepository;
    }

    @Override
    @Transactional
    public void insert(Book book) {
        for (Author author : book.getAuthors()) {
            authorRepository.insert(author);
        }
        for (Style style : book.getStyles()) {
            styleRepository.insert(style);
        }
        em.persist(book);
    }

    @Override
    @Transactional
    public Book getByName(String name) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where name = :name",
                Book.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b",
                Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(Book book) {
        em.remove(book);
    }

}
