package ru.otus.spring.hw18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.model.Author;
import ru.otus.spring.hw18.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final Author fallbackAuthor = new Author("-1", "N/A. Service is unavailable, try again later");

    @Override
    @HystrixCommand(fallbackMethod = "readAOneFallBack")
    public Author readByName(String name) {
        return Optional.ofNullable(authorRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Author with name '%s' not found", name)));
    }

    @Override
    @HystrixCommand(fallbackMethod = "readAllFallBack")
    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "readAOneFallBack")
    public Author readOrCreate(String name) {
        return Optional.ofNullable(authorRepository.findByName(name))
                .orElseGet(() -> authorRepository.save(new Author(name)));
    }

    private List<Author> readAllFallBack() {
        return List.of(fallbackAuthor);
    }

    private Author readAOneFallBack(String name) {
        return fallbackAuthor;
    }

}
