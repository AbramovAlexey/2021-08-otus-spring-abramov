package ru.otus.spring.hw5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw5.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJDBC implements AuthorDao{

    private final NamedParameterJdbcOperations parameterJdbcOperations;

    @Override
    public int count() {
        return parameterJdbcOperations.getJdbcOperations()
                                      .queryForObject("select count(*) from Authors", Integer.class);
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", author.getFullName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcOperations.update("insert into Authors (name) values (:name)",
                mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Author getById(long id) {
        return parameterJdbcOperations.queryForObject("select * from Authors where id = :id", Map.of("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return parameterJdbcOperations.query("select * from Authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        parameterJdbcOperations.update("delete Authors where id = :id",  Map.of("id", id));
    }

    @Override
    public void update(Author author) {
        parameterJdbcOperations.update("update Authors set fullName = :name where id = :id",
                Map.of("id", author.getId(), "name", author.getFullName()));
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getLong("id"), resultSet.getString("fullName"));
        }

    }
}
