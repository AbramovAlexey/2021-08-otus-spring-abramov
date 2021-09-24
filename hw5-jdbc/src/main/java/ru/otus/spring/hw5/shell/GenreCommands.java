package ru.otus.spring.hw5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw5.domain.Genre;
import ru.otus.spring.hw5.service.GenreService;

import java.util.List;

import static ru.otus.spring.hw5.utils.Utils.stringFromList;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

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

}
