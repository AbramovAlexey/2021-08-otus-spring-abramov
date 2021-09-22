package ru.otus.spring.hw5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw5.domain.Author;
import ru.otus.spring.hw5.domain.Book;
import ru.otus.spring.hw5.domain.Genre;
import ru.otus.spring.hw5.service.AuthorService;
import ru.otus.spring.hw5.service.BookService;
import ru.otus.spring.hw5.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @ShellMethod(value = "Create author", key = "createAuthor")
    public String createAuthor(@ShellOption String name) {
        long id = authorService.create(name);
        return String.format("Author has been successfully created with id = %s", id);
    }

    @ShellMethod(value = "Read all authors", key = "readAllAuthors")
    public String readAllAuthors() {
        List<Author> authors = authorService.readAll();
        return stringFromList(authors);
    }

    @ShellMethod(value = "Delete author", key = "deleteAuthor")
    public String deleteAuthor(@ShellOption long id) {
        authorService.delete(id);
        return "Author has been successfully deleted";
    }

    @ShellMethod(value = "Get author by id", key = "readAuthor")
    public String readAuthor(@ShellOption long id) {
        Author author = authorService.readById(id);
        return author.toString();
    }

    @ShellMethod(value = "Update author by id with fields", key = "updateAuthor")
    public String updateAuthor(@ShellOption long id, @ShellOption String newName) {
        authorService.update(id, newName);
        return "Author has been successfully updated";
    }

    @ShellMethod(value = "Create Genre", key = "createGenre")
    public String createGenre(@ShellOption String name) {
        long id = genreService.create(name);
        return String.format("Genre has been successfully created with id = %s", id);
    }

    @ShellMethod(value = "Read all Genres", key = "readAllGenres")
    public String readAllGenres() {
        List<Genre> genres = genreService.readAll();
        return stringFromList(genres);
    }

    @ShellMethod(value = "Delete Genre", key = "deleteGenre")
    public String deleteGenre(@ShellOption long id) {
        genreService.delete(id);
        return "Genre has been successfully deleted";
    }

    @ShellMethod(value = "Get Genre by id", key = "readGenre")
    public String readGenre(@ShellOption long id) {
        Genre genre = genreService.readById(id);
        return genre.toString();
    }

    @ShellMethod(value = "Update Genre by id with fields", key = "updateGenre")
    public String updateGenre(@ShellOption long id, @ShellOption String newName) {
        genreService.update(id, newName);
        return "Genre has been successfully updated";
    }

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
        return stringFromList(books);
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

    private String stringFromList(List<? extends Object> list) {
        return list.stream()
                   .map(Object::toString)
                   .collect(Collectors.joining(System.lineSeparator()));
    }

}
