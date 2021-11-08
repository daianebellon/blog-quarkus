package br.com.daianebellon.comment;

import javax.ws.rs.core.Response;

public interface CommentService {

    Response findById(Long id);

    Response register(Comment comment);

    Response deleteById(Long id);

    Response findAll();

}
