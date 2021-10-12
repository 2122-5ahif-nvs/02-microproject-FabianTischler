package at.htl.library.control;

import at.htl.library.entity.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository {
    @Inject
    EntityManager em;

    @Transactional
    public Book save(Book book) {
        persist(book);
        return book;
    }

    @Transactional
    public void update(Book book) {
        em.merge(book);
    }

    @Transactional
    public Book findById(long id) {
        return (Book)find("id", id).firstResult();
    }

    @Transactional
    public Object findByName(String name) {
        return find("name", name).firstResult();
    }

    @Transactional
    public Object findByNameAndId(String name, long id) {
        var t=  find("name = :name and id = :id", Parameters.with("name", name).and("id",id).map());
        return t.firstResult();
    }

    @Transactional
    public List<Book> findAllBooks() {
        return listAll();
    }

    @Transactional
    public void delete(long id) {
        delete("id", id);
    }

    @Transactional
    public long getNoOfBooksWithSameAuthor(Long id) {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b where b.author.id = :id", Long.class);
        return query.setParameter("id", id).getSingleResult();
    }
}
