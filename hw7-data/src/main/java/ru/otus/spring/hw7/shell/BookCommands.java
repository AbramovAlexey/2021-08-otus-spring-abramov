package ru.otus.spring.hw7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw7.model.Book;
import ru.otus.spring.hw7.service.BookService;
import ru.otus.spring.hw7.utils.Utils;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    @ShellMethod(value = "Create book", key = "createBook")
    public String createBook(@ShellOption String name) {
        long id = bookService.create(name);
        return String.format("Book has been successfully created with id = %s", id);
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
        return Objects.isNull(book) ? "Book not found" : book.toString();
    }

    @ShellMethod(value = "Update book by id with fields", key = "updateBook")
    public String updateBook(@ShellOption long id, @ShellOption String name) {
        bookService.update(id, name);
        return "Book has been successfully updated";
    }

    @ShellMethod(value = "Add comment to book", key = "addBookComment")
    public String addBookComment(@ShellOption long id, @ShellOption String content) {
        if (bookService.addBookComment(id, content)) {
            return "Comment has been successfully added";
        } else {
            return "Book with given id not found";
        }
    }

    @ShellMethod(value = "Add genre to book", key = "addBookGenre")
    public String addBookGenre(@ShellOption long id, @ShellOption long genreId) {
        if (bookService.addBookGenre(id, genreId)) {
            return "Genre has been successfully added";
        } else {
            return "Book or genre with given id not found";
        }
    }

    @ShellMethod(value = "Add author to book", key = "addBookAuthor")
    public String addBookAuthor(@ShellOption long id, @ShellOption long authorId) {
        if (bookService.addBookAuthor(id, authorId)) {
            return "Author has been successfully added";
        } else {
            return "Book or author with given id not found";
        }
    }

    @ShellMethod(value = "Remove comment from book", key = "deleteBookComment")
    public String deleteBookComment(@ShellOption long id, @ShellOption long commentId) {
        if (bookService.deleteBookComment(id, commentId)) {
            return "Comment has been successfully removed";
        } else {
            return "Book or comment with given id not found";
        }
    }

    @ShellMethod(value = "Delete genre from book", key = "deleteBookGenre")
    public String deleteBookGenre(@ShellOption long id, @ShellOption long genreId) {
        if (bookService.deleteBookGenre(id, genreId)) {
            return "Genre has been successfully removed";
        } else {
            return "Book or genre with given id not found";
        }
    }

    @ShellMethod(value = "Delete author from book", key = "deleteBookAuthor")
    public String deleteBookAuthor(@ShellOption long id, @ShellOption long authorId) {
        if (bookService.deleteBookAuthor(id, authorId)) {
            return "Author has been successfully deleted";
        } else {
            return "Book or author with given id not found";
        }
    }

}
