package ru.otus.spring.hw18.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.model.Genre;
import ru.otus.spring.hw18.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    public Genre readByName(String name) {
        return Optional.ofNullable(genreRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Genre with name '%s' not found", name)));
    }

    @Override
    public List<Genre> readAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre readOrCreate(String name) {
        return Optional.ofNullable(genreRepository.findByName(name))
                .orElseGet(() -> genreRepository.save(new Genre(name)));
    }

}
