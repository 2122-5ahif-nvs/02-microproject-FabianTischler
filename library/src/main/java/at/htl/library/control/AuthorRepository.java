package at.htl.library.control;

import at.htl.library.entity.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository {
    @Inject
    EntityManager em;

    @Transactional
    public Author save(Author author) {
        persist(author);
        return author;
    }

    @Transactional
    public void update(Author author) {
        em.merge(author);
    }

    @Transactional
    public Author findById(long id) {
        return (Author) find("id", id).firstResult();
    }

    @Transactional
    public Object findByName(String name) {
        return find("name", name).firstResult();
    }

    @Transactional
    public List<Author> findAllAuthors() {
        return listAll();
    }

    @Transactional
    public void delete(long id) {
        delete("id", id);
    }

}

