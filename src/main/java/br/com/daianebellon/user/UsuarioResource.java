package br.com.daianebellon.user;

import io.quarkus.security.jpa.Roles;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UsuarioResource {

    @Inject
    @Named("usuarioMongoService")
    UsuarioService service;

    @POST
    @Transactional
    @PermitAll
    public Response register(@RequestBody Usuario usuario) {
        return service.register(usuario);
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
       return service.findById(id);
    }

    @GET
    @Path("/log/{id}")
    public Response findDocumentId(@PathParam("id") String id) {
        return service.findDocumentId(id);
    }

    @GET
    @RolesAllowed("ADMIN")
    public Response findAll() {
        return service.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        return service.deleteById(id);
    }

    @DELETE
    @Path("/log/{id}")
    @Transactional
    public Response deleteDocumentId(@PathParam("id") String id) {
        return service.deleteDocumentId(id);
    }
}
