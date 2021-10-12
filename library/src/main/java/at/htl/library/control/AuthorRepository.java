package at.htl.library.control;

import at.htl.library.entity.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@OpenAPIDefinition(
        info = @Info(
                title = "Author Repo",
                description = "Repository for Authors",
                version = "1.0"
        )
)
public class AuthorRepository implements PanacheRepository {
    @Inject
    EntityManager em;

    @Transactional
    public Author save(Author author) {
        persist(author);
        return author;
    }

    @Transactional
    @Operation(
            summary = "update Author",
            description = "update an existing Author from the DB"
    )
    public void update(Author author) {
        em.merge(author);
    }

    @Transactional
    @Operation(
            summary = "find Author by ID",
            description = "find an Author with the ID"
    )
    public Author findById(long id) {
        return (Author) find("id", id).firstResult();
    }

    @Transactional
    @Operation(
            summary = "find Author by Name",
            description = "find an Author with the Name"
    )
    public Object findByName(String name) {
        return find("name", name).firstResult();
    }

    @Transactional
    @Operation(
            summary = "find Authors",
            description = "find all Authors"
    )
    public List<Author> findAllAuthors() {
        return listAll();
    }

    @Transactional
    @Operation(
            summary = "delete Author by ID",
            description = "delete an Author with the ID"
    )
    public void delete(long id) {
        delete("id", id);
    }

}

