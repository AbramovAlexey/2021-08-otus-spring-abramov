package ru.otus.spring.hw6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw6.model.Genre;
import ru.otus.spring.hw6.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public long create(String name) {
        Genre genre = new Genre(0, name);
        genreRepository.save(genre);
        return genre.getId();
    }

    @Override
    @Transactional
    public void update(long id, String name) {
        var genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            genre.get().setName(name);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Genre readById(long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> readAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        genreRepository.deleteById(id);
    }

}
