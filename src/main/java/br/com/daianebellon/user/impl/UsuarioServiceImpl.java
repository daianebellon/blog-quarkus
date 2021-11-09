package br.com.daianebellon.user.impl;

import br.com.daianebellon.user.Usuario;
import br.com.daianebellon.user.UsuarioService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("usuarioServicePostgres")
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    @Named("usuarioMongoService")
    UsuarioService service;

    @Override
    public Response register(Usuario usuario) {
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Usuario.add(usuario.nome, usuario.email, usuario.senha, usuario.role);
        service.register(usuario);

        return Response.ok(usuario).build();
    }

    @Override
    public Response deleteById(Long id) {
        boolean deletou = Usuario.deleteById(id);
        if (!deletou) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(deletou).build();
    }

    @Override
    public Response deleteDocumentId(String id) {
        boolean deletou = Usuario.deleteById(id);
        if (!deletou) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(deletou).build();
    }

    @Override
    public Response findById(Long id) {
        Optional<Usuario> user = Usuario.findByIdOptional(id);

        if (user.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.ifPresent(usuario -> usuario.senha = "");

        return Response.ok(user.get()).build();
    }

    @Override
    public Response findAll() {
        List<Usuario> usuarios = Usuario.findAll().list();

        if(usuarios == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        usuarios.forEach(usuario -> usuario.senha = "");

        return Response.ok(usuarios).build();
    }

    @Override
    public Response findDocumentId(String id) {
        return null;
    }


}
