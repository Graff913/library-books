package ru.graff.library.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.graff.library.domain.Style;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class StyleRepositoryJpa implements StyleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Style style) {
        em.persist(style);
    }

    @Override
    @Transactional
    public Style getByName(String name) {
        TypedQuery<Style> query = em.createQuery(
                "select s from Style s where s.name = :name",
                Style.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Style> getAll() {
        TypedQuery<Style> query = em.createQuery(
                "select s from Style s",
                Style.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(Style style) {
        em.remove(style);
    }

}
