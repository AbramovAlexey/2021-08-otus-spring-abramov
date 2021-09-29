package ru.otus.spring.hw6.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw6.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJPA implements GenreRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long count() {
        return em.createQuery("select count(g) from Genre g", Long.class)
                 .getSingleResult();
    }

    @Override
    public Genre save(Genre Genre) {
        if (Genre.getId() <= 0) {
            em.persist(Genre);
            return Genre;
        } else {
            return em.merge(Genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
