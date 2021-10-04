package ru.otus.spring.hw6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw6.model.Author;
import ru.otus.spring.hw6.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public long create(String name) {
        Author author = new Author(0, name);
        authorRepository.save(author);
        return author.getId();
    }

    @Override
    @Transactional
    public void update(long id, String fullName) {
        var author = authorRepository.findById(id);
        if (author.isPresent()) {
            author.get().setFullName(fullName);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Author readById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        authorRepository.deleteById(id);
    }

}
