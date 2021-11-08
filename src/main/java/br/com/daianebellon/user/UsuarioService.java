package br.com.daianebellon.user;

import javax.ws.rs.core.Response;

public interface UsuarioService {

    Response findById(Long id);

    Response register(Usuario usuario);

    Response deleteById(Long id);

    Response deleteDocumentId(String id);

    Response findAll();

    Response findDocumentId(String id);
}
