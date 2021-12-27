package ru.otus.spring.hw14.job.readers;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReaderCreator {

    <T> RepositoryItemReader<T> getReaderByRepository(JpaRepository<T, Long> repository);

}
