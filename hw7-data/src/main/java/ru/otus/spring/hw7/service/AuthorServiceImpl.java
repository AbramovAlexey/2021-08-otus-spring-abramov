package ru.otus.spring.hw7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw7.model.Author;
import ru.otus.spring.hw7.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Override
    public long create(String name) {
        Author author = new Author(0, name);
        authorRepository.save(author);
        return author.getId();
    }

    @Override
    public void update(long id, String fullName) {
        var author = authorRepository.findById(id);
        if (author.isPresent()) {
            author.get().setFullName(fullName);
        }
    }

    @Override
    public Author readById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    @Override
    public void delete(long id) {
        authorRepository.deleteById(id);
    }

}
