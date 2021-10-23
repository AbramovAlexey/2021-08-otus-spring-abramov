package ru.otus.spring.hw8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw8.model.Author;
import ru.otus.spring.hw8.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Override
    public String create(String name) {
        return authorRepository.save(new Author(name)).getId();
    }

    @Override
    public void update(String oldName, String newName) {
        Author author = readByName(oldName);
        author.setName(newName);
        authorRepository.save(author);
    }

    @Override
    public Author readByName(String name) {
        return Optional.ofNullable(authorRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Author with name '%s' not found", name)));
    }

    @Override
    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        authorRepository.deleteByName(name);
    }

}
