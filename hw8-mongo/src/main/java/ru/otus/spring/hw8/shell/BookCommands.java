package ru.otus.spring.hw8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw8.service.BookService;
import ru.otus.spring.hw8.utils.Utils;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    @ShellMethod(value = "Create book", key = "createBook")
    public String createBook(@ShellOption String name, @ShellOption String authorName, @ShellOption String genreName) {
        String id = bookService.create(name, authorName, genreName);
        return String.format("Book has been successfully created with id = %s", id);
    }

    @ShellMethod(value = "Read all books", key = "readAllBooks")
    public String readAllBooks() {
        return Utils.stringFromList(bookService.readAll());
    }

    @ShellMethod(value = "Read book", key = "readBook")
    public String readBook(@ShellOption String name) {
        return bookService.readByName(name).toString();
    }

    @ShellMethod(value = "Delete book", key = "deleteBook")
    public String deleteBook(@ShellOption String name) {
        bookService.deleteByName(name);
        return "Book has been successfully deleted";
    }

    @ShellMethod(value = "Update book", key = "updateBook")
    public String updateBook(@ShellOption String oldName, @ShellOption String newName) {
        bookService.update(oldName, newName);
        return "Book has been successfully updated";
    }

    @ShellMethod(value = "Add genre to book", key = "addBookGenre")
    public String addBookGenre(@ShellOption String nameBook, @ShellOption String nameGenre) {
        bookService.updateAddGenre(nameBook, nameGenre);
        return "Genre has been successfully added";
    }

    @ShellMethod(value = "Delete genre from book", key = "deleteBookGenre")
    public String deleteBookGenre(@ShellOption String nameBook, @ShellOption String nameGenre) {
        bookService.updateDeleteGenre(nameBook, nameGenre);
        return "Genre has been successfully deleted";
    }

    @ShellMethod(value = "Delete author from book", key = "deleteBookAuthor")
    public String deleteBookAuthor(@ShellOption String nameBook, @ShellOption String nameAuthor) {
        bookService.updateDeleteAuthor(nameBook, nameAuthor);
        return "Author has been successfully deleted";
    }

    @ShellMethod(value = "Add author to book", key = "addBookAuthor")
    public String addBookAuthor(@ShellOption String nameBook, @ShellOption String nameAuthor) {
        bookService.updateAddAuthor(nameBook, nameAuthor);
        return "Author has been successfully added";
    }

    @ShellMethod(value = "Show all comments for book", key = "showBookComments")
    public String showBookComments(@ShellOption String name) {
        return Utils.stringFromList(bookService.showAllComments(name));
    }

    @ShellMethod(value = "Add comment for book", key = "addBookComment")
    public String addBookComment(@ShellOption String nameBook, @ShellOption String content){
        String id = bookService.addComment(nameBook, content);
        return String.format("Comment has been successfully created with id = %s", id);
    }

    @ShellMethod(value = "Delete comment for book", key = "deleteBookComment")
    public String deleteBookComment(@ShellOption String id) {
        bookService.deleteComment(id);
        return "Comment has been successfully deleted";
    }

    @ShellMethod(value = "Find by author", key = "findBooksByAuthor")
    public String findBooksByAuthor(@ShellOption String name) {
        return Utils.stringFromList(bookService.findByAuthor(name));
    }

    @ShellMethod(value = "Find by genre", key = "findBooksByGenre")
    public String findBooksByGenre(@ShellOption String name) {
        return Utils.stringFromList(bookService.findByGenre(name));
    }

}
