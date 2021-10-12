package at.htl.library.control;

import at.htl.library.entity.Publisher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class PublisherRepository implements PanacheRepository<Publisher> {
    @Inject
    EntityManager em;

    @Transactional
    public Publisher save(Publisher publisher) {
        em.persist(publisher);
        return publisher;
    }

    @Transactional
    public void update(Publisher publisher) {
        em.merge(publisher);
    }

    //public Publisher findById(long id) {
        //return em.find(Publisher.class, id);
    //}

    public Object findByName(String name) {
        TypedQuery q =em.createQuery("select p from Publisher p where P_NAME = :v_name", Publisher.class);
        return q.setParameter("v_name", name).getSingleResult();
    }

    /*public List<Publisher> findAllPublishers() {
        TypedQuery<Publisher> query = em.createQuery("select p from Publisher p", Publisher.class);
        return query.getResultList();
    }*/

    /*@Transactional
    public void delete(long id) {
        Publisher p = em.find(Publisher.class, id);
        em.remove(p);
    }*/
}
