package ru.otus.spring.hw6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw6.model.Author;
import ru.otus.spring.hw6.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
