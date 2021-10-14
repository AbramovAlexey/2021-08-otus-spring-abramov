package ru.otus.spring.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw7.model.Author;
import ru.otus.spring.hw7.repository.AuthorRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Author должен")
@DataJpaTest
public class AuthorRepositoryJPATest {

    private static final int AUTHORS_COUNT = 2;
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy";
    public static final long EXISTING_SECOND_AUTHOR_ID = 2;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    public void shouldReturnCorrectCountAuthors() {
        assertThat(authorRepository.count()).isEqualTo(AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor(){
        Author expectedAuthor = new Author(0, "Tolkien");
        authorRepository.save(expectedAuthor);
        assertThat(expectedAuthor.getId()).isNotNull();
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor) ;
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        var existingAuthor = authorRepository.findById(EXISTING_AUTHOR_ID);
        assertThat(existingAuthor).isPresent().get().isNotNull();
        em.detach(existingAuthor.get());
        authorRepository.deleteById(EXISTING_AUTHOR_ID);
        assertThat(authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null)).isNull();
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isPresent().get()
                                .matches(a -> a.getId() == expectedAuthor.getId())
                                .matches(a -> a.getFullName().equals(expectedAuthor.getFullName()));
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(expectedAuthor).isNotNull();
        Author secondExpectedAuthor = authorRepository.findById(EXISTING_SECOND_AUTHOR_ID).orElse(null);
        assertThat(secondExpectedAuthor).isNotNull();
        List<Author> actualAuthorList = authorRepository.findAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor, secondExpectedAuthor);
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        String newFullName = "newFullName";
        Author author = authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(author).isNotNull();
        assertThat(author.getFullName()).isEqualTo(EXISTING_AUTHOR_NAME);
        em.detach(author);
        author.setFullName(newFullName);
        authorRepository.save(author);
        Author updatedAuthor = authorRepository.findById(EXISTING_AUTHOR_ID).orElse(null);
        assertThat(updatedAuthor.getFullName()).isEqualTo(newFullName);
    }

}
