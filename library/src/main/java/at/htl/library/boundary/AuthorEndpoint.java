package at.htl.library.boundary;

import at.htl.library.control.AuthorRepository;
import at.htl.library.entity.Author;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("author")
public class AuthorEndpoint {
    @Inject
    AuthorRepository authorRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Author author, @Context UriInfo info) {
        final Author savedAuthor = authorRepository.save(author);
        URI uri = info.getAbsolutePathBuilder().path("/book/" + savedAuthor.getId()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    public Response update(Author author) {
        Author newAuthor = new Author();
        newAuthor.setAuthorFirstName(author.getAuthorFirstName());
        newAuthor.setAuthorLastName(author.getAuthorLastName());
        newAuthor.setNationality(author.getNationality());
        authorRepository.update(newAuthor);
        return Response.ok(newAuthor).build();
    }

    @DELETE
    @Path("author/{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        authorRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<Author> authors =  authorRepository.findAllAuthors();
        return Response.ok(authors).build();
    }

    @GET
    @Path("author/findbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        final Author author = authorRepository.findById(id);
        return Response.ok(author).build();
    }
}
