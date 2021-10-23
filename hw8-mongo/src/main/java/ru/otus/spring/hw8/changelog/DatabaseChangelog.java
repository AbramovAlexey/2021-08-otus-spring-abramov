package ru.otus.spring.hw8.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.hw8.model.Author;
import ru.otus.spring.hw8.model.Book;
import ru.otus.spring.hw8.model.Genre;
import ru.otus.spring.hw8.repository.AuthorRepository;
import ru.otus.spring.hw8.repository.BookRepository;
import ru.otus.spring.hw8.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private Author author1 = new Author("Lermontov");
    private Author author2 = new Author("Tolstoy");
    private Genre genre = new Genre("Fantasy");
    private Book book = new Book("PeaceWar", List.of(author1, author2), List.of(genre));

    @ChangeSet(order = "001", id = "dropDb", author = "aabramov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "aabramov")
    public void initAuthors(AuthorRepository repository)
    {
        repository.save(author1);
        repository.save(author2);
    }

    @ChangeSet(order = "003", id = "initGenres", author = "aabramov")
    public void initGenres(GenreRepository repository)
    {
        repository.save(genre);
    }

    @ChangeSet(order = "004", id = "initBooks", author = "aabramov")
    public void initBooks(BookRepository repository)
    {
        repository.save(book);
    }

}
