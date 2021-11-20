package ru.otus.spring.hw10.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.hw10.dto.BookDto;
import ru.otus.spring.hw10.model.Book;
import ru.otus.spring.hw10.service.BookService;
import ru.otus.spring.hw10.service.DtoConverter;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final DtoConverter dtoConverter;

    @GetMapping("api/books")
    public List<BookDto> getBookList() {
        return dtoConverter.booksToDto(bookService.readAll());
    }

    @GetMapping("api/books/{id}")
    public BookDto getBookById(@PathVariable String id) {
        return dtoConverter.bookToDto(bookService.readById(id));
    }

    @DeleteMapping("api/books/{id}")
    public void deleteBookById(@PathVariable String id) {
        bookService.deleteById(id);
    }

    @PostMapping("api/books")
    public Book addBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.save(dtoConverter.DtoToBook(bookDto));
    }

    @PutMapping("api/books/{id}")
    public Book addBook(@Valid @RequestBody BookDto bookDto, @PathVariable String id) {
        Book bookToUpdate = dtoConverter.DtoToBook(bookDto);
        bookToUpdate.setId(id);
        return bookService.save(bookToUpdate);
    }

}
