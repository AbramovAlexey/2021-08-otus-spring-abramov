package ru.otus.spring.hw18.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.actuator.AddBookTracking;
import ru.otus.spring.hw18.actuator.DeleteBookTracking;
import ru.otus.spring.hw18.model.Author;
import ru.otus.spring.hw18.model.Book;
import ru.otus.spring.hw18.model.Comment;
import ru.otus.spring.hw18.model.Genre;
import ru.otus.spring.hw18.repository.BookRepository;
import ru.otus.spring.hw18.repository.CommentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final Book fallbackBook = new Book("-1", "N/A. Service is unavailable, try again later", Collections.emptyList(), Collections.emptyList());

    @Override
    @HystrixCommand(fallbackMethod = "readOneFallBack")
    public Book readByName(String name) {
        return Optional.ofNullable(bookRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Book with name '%s' not found", name)));
    }

    @Override
    @HystrixCommand(fallbackMethod = "readAllFallBack")
    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    @Override
    @HystrixCommand(fallbackMethod = "readOneFallBack")
    public Book readById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @DeleteBookTracking
    @HystrixCommand(fallbackMethod = "deleteFallBack")
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @AddBookTracking
    @HystrixCommand(fallbackMethod = "readOneFallBackBook")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    private List<Book> readAllFallBack() {
        return List.of(fallbackBook);
    }

    private Book readOneFallBack(String id) {
        return fallbackBook;
    }

    private Book readOneFallBackBook(Book book) {
        return fallbackBook;
    }

    private void deleteFallBack(String id) {
    }

}
