package ru.otus.spring.hw5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw5.dao.AuthorDao;
import ru.otus.spring.hw5.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorDao authorDao;

    @Override
    public long create(String name) {
        Author author = new Author(0, name);
        return authorDao.insert(author);
    }

    @Override
    public void update(long id, String fullName) {
        Author author = new Author(id, fullName);
        authorDao.update(author);
    }

    @Override
    public Author readById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> readAll() {
        return authorDao.getAll();
    }

    @Override
    public void delete(long id) {
        authorDao.deleteById(id);
    }

}
