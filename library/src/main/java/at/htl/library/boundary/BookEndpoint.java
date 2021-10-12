package at.htl.library.boundary;

import at.htl.library.control.BookRepository;
import at.htl.library.entity.Book;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("book")
public class BookEndpoint {
    @Inject
    BookRepository bookRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Book book, @Context UriInfo info) {
        final Book savedBook = bookRepository.save(book);
        URI uri = info.getAbsolutePathBuilder().path("/book/" + savedBook.getId()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    public Response update(Book book) {
        Book newBook = new Book();
        newBook.setBookName(book.getBookName());
        newBook.setPublisher(book.getPublisher());
        newBook.setAuthor(book.getAuthor());
        newBook.setLent(book.getLent());
        bookRepository.update(newBook);
        return Response.ok(newBook).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        final List<Book> books =  bookRepository.findAllBooks();
        return Response.ok(books).build();
    }

    @GET
    @Path("findbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        final Book book = bookRepository.findById(id);
        return Response.ok(book).build();
    }

    @GET
    @Path("findbyname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name) {
        final Book book = (Book) bookRepository.findByName(name);
        return Response.ok(book).build();
    }

    @GET
    @Path("publishedBooks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response publishedBooks(@PathParam("id")Long id){
        long nob = bookRepository.getNoOfBooksWithSameAuthor(id);
        return Response.ok(nob).build();
    }

    @GET
    @Path("findbyname/{name}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNameAndId(@PathParam("name") String name, @PathParam("id") long id) {
        final Book book = (Book) bookRepository.findByNameAndId(name, id);
        return Response.ok(book).build();
    }
}

