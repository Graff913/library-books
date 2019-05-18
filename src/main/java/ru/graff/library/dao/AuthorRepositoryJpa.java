package ru.graff.library.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    @Transactional
    public Author getByName(String name) {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a where name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a",
                Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(Author author) {
        em.remove(author);
    }

}
