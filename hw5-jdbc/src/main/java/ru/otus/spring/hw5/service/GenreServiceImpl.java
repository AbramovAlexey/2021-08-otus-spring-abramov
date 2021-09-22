package ru.otus.spring.hw5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw5.dao.GenreDao;
import ru.otus.spring.hw5.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreDao GenreDao;

    @Override
    public long create(String name) {
        Genre genre = new Genre(0, name);
        return GenreDao.insert(genre);
    }

    @Override
    public void update(long id, String name) {
        Genre genre = new Genre(id, name);
        GenreDao.update(genre);
    }

    @Override
    public Genre readById(long id) {
        return GenreDao.getById(id);
    }

    @Override
    public List<Genre> readAll() {
        return GenreDao.getAll();
    }

    @Override
    public void delete(long id) {
        GenreDao.deleteById(id);
    }

}
