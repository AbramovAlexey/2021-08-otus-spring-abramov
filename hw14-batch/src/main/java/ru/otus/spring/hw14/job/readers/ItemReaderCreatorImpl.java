package ru.otus.spring.hw14.job.readers;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ItemReaderCreatorImpl implements ItemReaderCreator{

    @Override
    public <T> RepositoryItemReader<T> getReaderByRepository(JpaRepository<T, Long> repository) {
        Map<String, Sort.Direction> sort = Map.of("id", Sort.Direction.ASC);
        return new RepositoryItemReaderBuilder<T>().repository(repository)
                .methodName("findAll")
                .sorts(sort)
                .name(repository.getClass().getName())
                .build();
    }

}
