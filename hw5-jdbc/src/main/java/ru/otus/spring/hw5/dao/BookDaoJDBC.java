package ru.otus.spring.hw5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw5.domain.Author;
import ru.otus.spring.hw5.domain.Book;
import ru.otus.spring.hw5.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJDBC implements BookDao{

    private final NamedParameterJdbcOperations parameterJdbcOperations;

    @Override
    public int count() {
        return parameterJdbcOperations.getJdbcOperations()
                .queryForObject("select count(*) from Books", Integer.class);
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", book.getName());
        mapSqlParameterSource.addValue("authorId", book.getAuthor().getId());
        mapSqlParameterSource.addValue("genreId", book.getGenre().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcOperations.update("insert into Books (name, authorId, genreId) values (:name, :authorId, :genreId)",
                mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Book getById(long id) {
        return parameterJdbcOperations.queryForObject("select b.id, b.name, b.authorId, b.genreId, a.fullName as authorName, g.name as genreName from Books b " +
                                                            "left join Authors a on a.id = b.authorId " +
                                                            "left join Genres g on g.id = b.genreId " +
                                                         "where b.id = :id ", Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return parameterJdbcOperations.query("select b.id, b.name, b.authorId, b.genreId, a.fullName as authorName, g.name as genreName from Books b " +
                                                    "left join Authors a on a.id = b.authorId " +
                                                    "left join Genres g on g.id = b.genreId ", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        parameterJdbcOperations.update("delete Books where id = :id",  Map.of("id", id));
    }

    @Override
    public void update(Book book) {
        parameterJdbcOperations.update("update Books set name = :name, authorId = :authorId, genreId = :genreId where id = :id",
                Map.of("id", book.getId(), "name", book.getName(),
                       "authorId", book.getAuthor().getId(), "genreId", book.getGenre().getId()));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(resultSet.getLong("id"),resultSet.getString("name"),
                            new Author(resultSet.getLong("authorId"), resultSet.getString("authorName")),
                            new Genre(resultSet.getLong("genreId"), resultSet.getString("genreName")));
        }

    }
}
