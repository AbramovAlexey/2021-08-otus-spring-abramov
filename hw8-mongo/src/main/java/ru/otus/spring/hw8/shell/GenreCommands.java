package ru.otus.spring.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw8.model.Genre;
import ru.otus.spring.hw8.service.GenreService;
import ru.otus.spring.hw8.utils.Utils;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Get genre by name", key = "readGenre")
    public String readGenre(@ShellOption String name) {
        Genre genre = genreService.readByName(name);
        return genre.toString();
    }

    @ShellMethod(value = "Create genre", key = "createGenre")
    public String createGenre(@ShellOption String name) {
        String id = genreService.create(name);
        return String.format("Genre has been successfully created with id = %s", id);
    }

    @ShellMethod(value = "Read all genres", key = "readAllGenres")
    public String readAllGenres() {
        return Utils.stringFromList(genreService.readAll());
    }

    @ShellMethod(value = "Delete genre", key = "deleteGenre")
    public String deleteGenre(@ShellOption String name) {
        genreService.deleteByName(name);
        return "Genre has been successfully deleted";
    }

    @ShellMethod(value = "Update genre by name", key = "updateGenre")
    public String updateGenre(@ShellOption String oldName, @ShellOption String newName) {
        genreService.update(oldName, newName);
        return "Genre has been successfully updated";
    }


}
