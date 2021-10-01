package ru.otus.spring.hw6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw6.model.Genre;
import ru.otus.spring.hw6.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    @Override
    public long create(String name) {
        Genre genre = new Genre(0, name);
        genreRepository.save(genre);
        return genre.getId();
    }

    @Override
    public void update(long id, String name) {
        var genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            genre.get().setName(name);
        }
    }

    @Override
    public Genre readById(long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public List<Genre> readAll() {
        return genreRepository.findAll();
    }

    @Override
    public void delete(long id) {
        genreRepository.deleteById(id);
    }

}
