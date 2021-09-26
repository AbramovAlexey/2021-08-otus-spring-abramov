package ru.otus.spring.hw5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw5.dao.BookDao;
import ru.otus.spring.hw5.domain.Author;
import ru.otus.spring.hw5.domain.Book;
import ru.otus.spring.hw5.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;

    @Override
    public long create(String name, long authorId, long genreId) {
        try {
            Book book = new Book(name, authorId, genreId);
            return bookDao.insert(book);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return -1;
        }
    }

    @Override
    public boolean update(long id, String name, long authorId, long genreId) {
        Book book = new Book(id, name, authorId, genreId);
        try {
            bookDao.update(book);
            return true;
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return false;
        }
    }

    @Override
    public Book readById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> readAll() {
        return bookDao.getAll();
    }

    @Override
    public void delete(long id) {
        bookDao.deleteById(id);
    }

}
