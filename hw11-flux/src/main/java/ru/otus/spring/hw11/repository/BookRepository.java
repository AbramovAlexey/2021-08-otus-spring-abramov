package ru.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw11.model.Book;

@Component
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
