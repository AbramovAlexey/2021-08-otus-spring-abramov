package ru.otus.spring.hw6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw6.model.Book;
import ru.otus.spring.hw6.service.BookService;
import ru.otus.spring.hw6.utils.Utils;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    @ShellMethod(value = "Create book", key = "createBook")
    public String createBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        long id = bookService.create(name, authorId, genreId);
        if (id == -1 ) {
            return "Error during create book. Check that author and genre with entered id exists";
        } else {
            return String.format("Book has been successfully created with id = %s", id);
        }
    }

    @ShellMethod(value = "Read all books", key = "readAllBooks")
    public String readAllBooks() {
        List<Book> books = bookService.readAll();
        return Utils.stringFromList(books);
    }

    @ShellMethod(value = "Delete book", key = "deleteBook")
    public String deleteBook(@ShellOption long id) {
        bookService.delete(id);
        return "Book has been successfully deleted";
    }

    @ShellMethod(value = "Get book by id", key = "readBook")
    public String readBook(@ShellOption long id) {
        Book book = bookService.readById(id);
        return book.toString();
    }

    @ShellMethod(value = "Update book by id with fields", key = "updateBook")
    public String updateBook(@ShellOption long id, @ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        if (bookService.update(id, name, authorId, genreId)) {
            return "Book has been successfully updated";
        } else {
            return "Error during update book. Check that author and genre with entered id exists";
        }
    }

}
