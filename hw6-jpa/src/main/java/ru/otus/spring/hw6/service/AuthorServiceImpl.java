package ru.otus.spring.hw6.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw6.model.Author;
import ru.otus.spring.hw6.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;


    @Override
    public long create(String name) {
        return 0;
    }

    @Override
    public void update(long id, String fullName) {

    }

    @Override
    public Author readById(long id) {
        return null;
    }

    @Override
    public List<Author> readAll() {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
