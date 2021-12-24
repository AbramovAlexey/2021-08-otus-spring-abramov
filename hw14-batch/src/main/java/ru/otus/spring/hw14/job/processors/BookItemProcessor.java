package ru.otus.spring.hw14.job.processors;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.model.*;
import ru.otus.spring.hw14.repository.IdRelationRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookItemProcessor implements ItemProcessor<Book, BookMongo> {

    private final RelationCacheService relationCacheService;

    @Override
    public BookMongo process(Book item) throws Exception {
        return new BookMongo(item.getName(),
                item.getAuthors()
                        .stream()
                        .map(it -> new AuthorMongo(relationCacheService.getRelationId(it.getId(), AuthorMongo.class)))
                        .collect(Collectors.toList()),
                item.getGenres().stream()
                        .map(it -> new GenreMongo(relationCacheService.getRelationId(it.getId(), GenreMongo.class)))
                        .collect(Collectors.toList()));
    }

}
