package ru.otus.spring.hw8.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.hw8.model.Author;
import ru.otus.spring.hw8.repository.AuthorRepository;

@ChangeLog
public class DatabaseChangelog {

    private Author authorLermontov;
    private Author authorTolstoy;

    @ChangeSet(order = "001", id = "dropDb", author = "aabramov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "aabramov", runAlways = true)
    public void initAuthors(AuthorRepository repository)
    {
        authorLermontov = repository.save(new Author("Lermontov"));
        authorTolstoy = repository.save(new Author("Tolstoy"));
    }

}
