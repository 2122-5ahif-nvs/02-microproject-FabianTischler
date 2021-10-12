package at.htl.library.boundary;

import at.htl.library.control.PublisherRepository;
import at.htl.library.entity.Publisher;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("publisher")
public class PublisherEndpoint {
    @Inject
    PublisherRepository publisherRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Publisher publisher, @Context UriInfo info) {
        final Publisher savedPublisher = publisherRepository.save(publisher);
        URI uri = info.getAbsolutePathBuilder().path("/book/" + savedPublisher.getId()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    public Response update(Publisher publisher) {
        Publisher newPublisher = new Publisher();
        newPublisher.setPublisherName(publisher.getPublisherName());
        publisherRepository.save(newPublisher);
        return Response.ok(newPublisher).build();
    }

    @DELETE
    @Path("publisher/{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        boolean deleted = publisherRepository.deleteById(id);
        return Response.ok(deleted).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllPublishers() {
        List<Publisher> publishers = publisherRepository.listAll();
        return Response.ok(publishers).build();
    }

    @GET
    @Path("publisher/findbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        final Publisher publisher = publisherRepository.findById(id);
        return Response.ok(publisher).build();
    }

    @GET
    @Path("publisher/findbyname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name) {
        final Publisher publisher = (Publisher) publisherRepository.findByName(name);
        return Response.ok(publisher).build();
    }
}
