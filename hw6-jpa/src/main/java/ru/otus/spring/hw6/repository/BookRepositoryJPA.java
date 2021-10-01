package ru.otus.spring.hw6.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw6.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookRepositoryJPA implements BookRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long count() {
        return em.createQuery("select count(b) from Book b", Long.class)
                 .getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> findWithGenreName(String genreName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genres g " +
                                                   "where g.name = :name", Book.class);
        query.setParameter("name", genreName);
        return query.getResultList();
    }

    @Override
    public List<Book> findWithAuthorName(String authorName) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.authors a " +
                                                   "where a.fullName = :name", Book.class);
        query.setParameter("name", authorName);
        return query.getResultList();
    }

}
