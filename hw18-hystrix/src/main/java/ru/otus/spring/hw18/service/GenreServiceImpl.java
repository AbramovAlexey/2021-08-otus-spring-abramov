package ru.otus.spring.hw18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.model.Author;
import ru.otus.spring.hw18.model.Genre;
import ru.otus.spring.hw18.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final Genre fallbackGenre = new Genre("-1", "N/A. Service is unavailable, try again later");

    @Override
    @HystrixCommand(fallbackMethod = "readAOneFallBack")
    public Genre readByName(String name) {
        return Optional.ofNullable(genreRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Genre with name '%s' not found", name)));
    }

    @Override
    @HystrixCommand(fallbackMethod = "readAllFallBack")
    public List<Genre> readAll() {
        return genreRepository.findAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "readAOneFallBack")
    public Genre readOrCreate(String name) {
        return Optional.ofNullable(genreRepository.findByName(name))
                .orElseGet(() -> genreRepository.save(new Genre(name)));
    }

    private List<Genre> readAllFallBack() {
        return List.of(fallbackGenre);
    }

    private Genre readAOneFallBack(String name) {
        return fallbackGenre;
    }

}
