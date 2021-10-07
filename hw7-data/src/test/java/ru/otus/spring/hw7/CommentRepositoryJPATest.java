package ru.otus.spring.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.hw7.model.Book;
import ru.otus.spring.hw7.model.Comment;
import ru.otus.spring.hw7.repository.BookRepository;
import ru.otus.spring.hw7.repository.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Comment должен")
@DataJpaTest
public class CommentRepositoryJPATest {

    private static final int EXPECTED_COMMENT_COUNT = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private static final long EXISTING_COMMENT_ID = 1;
    private static final String EXISTING_COMMENT_CONTENT = "Bad book";

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество комментариев в БД")
    @Test
    public void shouldReturnCorrectCountComments() {
        assertThat(commentRepository.count()).isEqualTo(EXPECTED_COMMENT_COUNT);
    }

    @DisplayName("добавлять комментарий в БД")
    @Test
    void shouldInsertComment(){
        Book existingBook = bookRepository.findById(EXISTING_BOOK_ID).orElse(null);
        Comment comment = new Comment(0, "Good book", existingBook);
        commentRepository.save(comment);
        assertThat(comment.getId()).isGreaterThan(0);
        em.detach(comment);
        var actualBookComment =  commentRepository.findById(comment.getId());
        assertThat(actualBookComment).isPresent().get()
                                     .usingRecursiveComparison()
                                     .isEqualTo(comment);
    }


    @DisplayName("удалять заданный комментарий по его id")
    @Test
    void shouldCorrectDeleteCommentById() {
        Comment comment = commentRepository.findById(EXISTING_COMMENT_ID).orElse(null);
        assertThat(comment).isNotNull();
        commentRepository.deleteById(comment.getId());
        em.flush();
        assertThat(commentRepository.findById(EXISTING_COMMENT_ID)).isNotPresent();
    }

    @DisplayName("возвращать ожидаемый комментарий по его id")
    @Test
    void shouldReturnExpectedCommentById() {
        Comment comment = commentRepository.findById(EXISTING_COMMENT_ID).orElse(null);
        assertThat(comment).isNotNull();
        assertThat(comment).matches(c -> c.getId() == EXISTING_COMMENT_ID)
                           .matches(c -> c.getBook().getId() == EXISTING_BOOK_ID)
                           .matches(c -> c.getContent().equals(EXISTING_COMMENT_CONTENT));

    }

    @DisplayName("возвращать ожидаемый список комментариев")
    @Test
    void shouldReturnExpectedCommentsList() {
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList).hasSize(EXPECTED_COMMENT_COUNT);
        assertThat(commentList).allMatch(c -> c.getId() == EXISTING_COMMENT_ID)
                               .allMatch(c -> c .getContent().equals(EXISTING_COMMENT_CONTENT));
    }

    @DisplayName("обновлять комментарий в БД")
    @Test
    void shouldUpdateComment() {
        String newContent = "Good book";
        Comment comment = commentRepository.findById(EXISTING_COMMENT_ID).orElse(null);
        comment.setContent(newContent);
        commentRepository.save(comment);
        em.flush();
        em.detach(comment);
        Comment updatedComment = commentRepository.findById(EXISTING_COMMENT_ID).orElse(null);
        assertThat(updatedComment.getContent()).isEqualTo(newContent);
    }

}
