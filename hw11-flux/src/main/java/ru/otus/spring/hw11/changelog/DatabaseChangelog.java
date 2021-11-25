package ru.otus.spring.hw11.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.hw11.model.Author;
import ru.otus.spring.hw11.model.Book;
import ru.otus.spring.hw11.model.Comment;
import ru.otus.spring.hw11.model.Genre;
import ru.otus.spring.hw11.repository.AuthorRepository;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.CommentRepository;
import ru.otus.spring.hw11.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final Author author1 = new Author("Tolkien");
    private final Author author2 = new Author("Tolstoy");
    private final Genre genre = new Genre("Fantasy");
    private final Book book = new Book("LOTR", List.of(author1, author2), List.of(genre));

    @ChangeSet(order = "001", id = "dropDb", author = "aabramov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "aabramov", runAlways = true)
    public void initAuthors(AuthorRepository repository)
    {
        repository.save(author1);
        repository.save(author2);
    }

    @ChangeSet(order = "003", id = "initGenres", author = "aabramov", runAlways = true)
    public void initGenres(GenreRepository repository)
    {
        repository.save(genre);
    }

    @ChangeSet(order = "004", id = "initBooks", author = "aabramov", runAlways = true)
    public void initBooks(BookRepository repository)
    {
        repository.save(book);
    }

    @ChangeSet(order = "005", id = "initComments", author = "aabramov", runAlways = true)
    public void initComments(CommentRepository repository)
    {
        repository.save(new Comment(book, "Good book!"));
    }

}
