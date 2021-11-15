package ru.otus.spring.hw8.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw8.model.Book;
import ru.otus.spring.hw8.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("api/books")
    public List<Book> getBookList() {
        return bookService.readAll();
    }

}
