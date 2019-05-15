package ru.graff.library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.graff.library.domain.Author;
import ru.graff.library.domain.Book;
import ru.graff.library.domain.Style;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final StyleDao styleDao;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       AuthorDao authorDao, StyleDao styleDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.styleDao = styleDao;
    }

    @Override
    public int insert(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", book.getName());
        namedParameterJdbcOperations.update("insert into books (`name`) values (:name)", params);
        Book insertBook = getByName(book.getName());
        for (Author author : book.getAuthors()) {
            linkAuthor(insertBook, author);
        }
        for (Style style : book.getStyles()) {
            linkStyle(insertBook, style);
        }
        return insertBook.getId();
    }

    @Override
    public void linkAuthor(Book book, Author author) {
        int bookId = book.getId() == 0 ? getByName(book.getName()).getId() : book.getId();
        int authorId = authorDao.insert(author);
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", bookId);
        params.put("author_id", authorId);
        namedParameterJdbcOperations.update("insert into link_author (book_id, author_id) values (:book_id, :author_id)", params);
    }

    @Override
    public void linkStyle(Book book, Style style) {
        int bookId = book.getId() == 0 ? getByName(book.getName()).getId() : book.getId();
        int styleId = styleDao.insert(style);
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", bookId);
        params.put("style_id", styleId);
        namedParameterJdbcOperations.update("insert into link_style (book_id, style_id) values (:book_id, :style_id)", params);
    }

    @Override
    public Book getByName(String name) {
        try {
            Map<String, Object> params = Collections.singletonMap("name", name);
            return namedParameterJdbcOperations.queryForObject("select * from books where `name` = :name", params, new BookMapper(authorDao, styleDao));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query ("select * from books", new BookMapper(authorDao, styleDao));
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        private AuthorDao authorDao;
        private StyleDao styleDao;

        public BookMapper(AuthorDao authorDao, StyleDao styleDao) {
            super();
            this.authorDao = authorDao;
            this.styleDao = styleDao;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Book book = new Book(id, name);
            book.setAuthors(authorDao.getAuthorsByBookId(id));
            book.setStyles(styleDao.getStylesByBookId(id));
            return book;
        }

    }

}
