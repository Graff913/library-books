package ru.graff.library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.graff.library.domain.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert(Author author) {
        Author a = getByName(author.getName());
        if (a != null) {
            return a.getId();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("name", author.getName());
        namedParameterJdbcOperations.update("insert into author (`name`) values (:name)", params);
        return getByName(author.getName()).getId();
    }

    @Override
    public Author getByName(String name) {
        try {
            Map<String, Object> params = Collections.singletonMap("name", name);
            return namedParameterJdbcOperations.queryForObject("select * from author where `name` = :name", params, new AuthorMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from author", new AuthorMapper());
    }

    @Override
    public List<Author> getAuthorsByBookId(int bookId) {
        Map<String, Object> params = Collections.singletonMap("book_id", bookId);
        return namedParameterJdbcOperations.query(
                "select * from author s where s.id in (select l.author_id from link_author l where l.book_id = :book_id)",
                params, new AuthorMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from author where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }

    }

}
