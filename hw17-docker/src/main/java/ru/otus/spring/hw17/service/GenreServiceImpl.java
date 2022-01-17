package ru.otus.spring.hw17.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw17.model.Genre;
import ru.otus.spring.hw17.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    public String create(String name) {
        return genreRepository.save(new Genre(name)).getId();
    }

    @Override
    public void update(String oldName, String newName) {
        Genre genre = readByName(oldName);
        genre.setName(newName);
        genreRepository.save(genre);
    }

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
    public void deleteByName(String name) {
        genreRepository.deleteByName(name);
    }

    @Override
    public Genre readOrCreate(String name) {
        return Optional.ofNullable(genreRepository.findByName(name))
                .orElseGet(() -> genreRepository.save(new Genre(name)));
    }

}
