package ru.otus.spring.hw8.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw8.model.Author;
import ru.otus.spring.hw8.model.Book;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Component
@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Author> findAuthorsFromBooks() {
        final var aggregation = newAggregation(project().andExclude("_id").and("authors"),
                                                          unwind("authors"),
                                                          group("authors._id", "authors.name"),
                                                          project().andInclude("_id._id", "_id.name"));
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class)
                            .getMappedResults();
    }

}
