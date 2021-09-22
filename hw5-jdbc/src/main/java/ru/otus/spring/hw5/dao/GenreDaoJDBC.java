package ru.otus.spring.hw5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw5.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJDBC implements GenreDao{

    private final NamedParameterJdbcOperations parameterJdbcOperations;

    @Override
    public int count() {
        return parameterJdbcOperations.getJdbcOperations()
                .queryForObject("select count(*) from Genres", Integer.class);
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcOperations.update("insert into Genres (name) values (:name)",
                mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Genre getById(long id) {
        return parameterJdbcOperations.queryForObject("select * from Genres where id = :id", Map.of("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return parameterJdbcOperations.query("select * from Genres", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        parameterJdbcOperations.update("delete Genres where id = :id",  Map.of("id", id));
    }

    @Override
    public void update(Genre genre) {
        parameterJdbcOperations.update("update Genres set name = :name where id = :id",
            Map.of("id",genre.getId(), "name", genre.getName()));
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getLong("id"), resultSet.getString("name"));
        }

    }
}
