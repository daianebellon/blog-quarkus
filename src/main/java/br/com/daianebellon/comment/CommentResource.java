package br.com.daianebellon.comment;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentResource {

    @Inject
    CommentService service;

    @GET
    public Response findAll() {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response register(@RequestBody Comment comment) {
        return service.register(comment);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        return service.deleteById(id);
    }

}
