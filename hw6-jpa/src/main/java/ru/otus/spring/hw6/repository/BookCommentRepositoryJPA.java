package ru.otus.spring.hw6.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw6.model.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookCommentRepositoryJPA implements BookCommentRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long count() {
        return em.createQuery("select count(bc) from BookComment bc", Long.class)
                 .getSingleResult();
    }

    @Override
    public BookComment save(BookComment bookComment) {
        if (bookComment.getId() <= 0) {
            em.persist(bookComment);
            return bookComment;
        } else {
            return em.merge(bookComment);
        }
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }

    @Override
    public List<BookComment> findAll() {
        TypedQuery<BookComment> query = em.createQuery("select bc from BookComment bc", BookComment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from BookComment a where a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
