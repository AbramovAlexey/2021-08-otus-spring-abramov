package ru.otus.spring.hw5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw5.domain.Author;
import ru.otus.spring.hw5.service.AuthorService;

import java.util.List;

import static ru.otus.spring.hw5.utils.Utils.stringFromList;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;

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

}
