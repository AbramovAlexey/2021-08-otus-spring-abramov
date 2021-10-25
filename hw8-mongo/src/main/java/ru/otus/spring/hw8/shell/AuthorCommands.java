package ru.otus.spring.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw8.model.Author;
import ru.otus.spring.hw8.service.AuthorService;
import ru.otus.spring.hw8.utils.Utils;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Get author by name", key = "readAuthor")
    public String readAuthor(@ShellOption String name) {
        Author author = authorService.readByName(name);
        return author.toString();
    }

    @ShellMethod(value = "Create author", key = "createAuthor")
    public String createAuthor(@ShellOption String name) {
        String id = authorService.create(name);
        return String.format("Author has been successfully created with id = %s", id);
    }

    @ShellMethod(value = "Read all authors", key = "readAllAuthors")
    public String readAllAuthors() {
        return Utils.stringFromList(authorService.readAll());
    }

    @ShellMethod(value = "Delete author", key = "deleteAuthor")
    public String deleteAuthor(@ShellOption String name) {
        authorService.deleteByName(name);
        return "Author has been successfully deleted";
    }

    @ShellMethod(value = "Update author by name", key = "updateAuthor")
    public String updateAuthor(@ShellOption String oldName, @ShellOption String newName) {
        authorService.update(oldName, newName);
        return "Author has been successfully updated";
    }

    @ShellMethod(value = "Check authors consistency", key = {"checkAuthorConsistency", "chkAuths"})
    public String checkAuthorConsistency(){
        return authorService.checkAuthorsConsistency() ? "OK" : "ERROR - differences found";
    }

}
