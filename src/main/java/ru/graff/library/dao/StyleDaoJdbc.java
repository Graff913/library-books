package ru.graff.library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.graff.library.domain.Style;
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
public class StyleDaoJdbc implements StyleDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StyleDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert(Style style) {
        Style s = getByName(style.getName());
        if (s != null) {
            return s.getId();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("name", style.getName());
        namedParameterJdbcOperations.update("insert into style (`name`) values (:name)", params);
        return getByName(style.getName()).getId();
    }

    @Override
    public Style getByName(String name) {
        try {
            Map<String, Object> params = Collections.singletonMap("name", name);
            return namedParameterJdbcOperations.queryForObject("select * from style where `name` = :name", params, new StyleMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Style> getAll() {
        return namedParameterJdbcOperations.query("select * from style", new StyleMapper());
    }

    @Override
    public List<Style> getStylesByBookId(int bookId) {
        Map<String, Object> params = Collections.singletonMap("book_id", bookId);
        return namedParameterJdbcOperations.query(
                "select * from style s where s.id in (select l.style_id from link_style l where l.book_id = :book_id)",
                params, new StyleMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from style where id = :id", params);
    }

    private static class StyleMapper implements RowMapper<Style> {

        @Override
        public Style mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Style(id, name);
        }

    }

}
