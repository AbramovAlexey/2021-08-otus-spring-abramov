package ru.otus.spring.hw13.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.Index;
import ru.otus.spring.hw13.model.*;
import ru.otus.spring.hw13.repository.*;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private final Author author1 = new Author("Tolkien");
    private final Author author2 = new Author("Tolstoy");
    private final Genre genre = new Genre("Fantasy");
    private final Book book = new Book("LOTR", List.of(author1, author2), List.of(genre));
    private final List<Role> roles = List.of(new Role("ROLE_USER"), new Role("ROLE_MANAGER"));
    private final List<AppUser> users = List.of(new AppUser("user", "$2a$10$WTAIgpwhOTDqxUu5yOz5NebhXTB.ub7P2SdBbjm1qsRWYzCu2sE7u", List.of(roles.get(0))),
                                                new AppUser("manager", "$2a$10$7ye2HJh06SybOPmpKuOKw.O83K/1z4IvERnW2JLGd4bdklrzrzR9a", List.of(roles.get(1))),
                                                new AppUser("admin", "$2a$10$lQf9bIvxe.HP4B01X7TcpOqklTqxWzDx.0ruKJ5CMmlhRtjCQ/gNS"));

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

    @ChangeSet(order = "005", id = "initRoles", author = "aabramov", runAlways = true)
    public void initRoles(RoleRepository repository)
    {
        roles.forEach(repository::save);
    }

    @ChangeSet(order = "006", id = "initUser", author = "aabramov", runAlways = true)
    public void initBooks(AppUserRepository repository)
    {
        users.forEach(repository::save);
    }

    @ChangeSet(order = "007", id = "createExpireIndex", author = "aabramov", runAlways = true)
    public void createIndex(MongockTemplate mongoTemplate)
    {
        mongoTemplate.indexOps("tokenBlacklist")
                     .ensureIndex(new Index().on("expireTo", Sort.Direction.ASC)
                     .expire(10));
    }


}
