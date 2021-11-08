package br.com.daianebellon.comment.impl;

import br.com.daianebellon.comment.Comment;
import br.com.daianebellon.comment.CommentService;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CommentServiceImpl implements CommentService {

    @Override
    public Response register(Comment comment) {
        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Comment.persist(comment);
        return Response.ok(comment).build();
    }

    @Override
    public Response deleteById(Long id) {
        var comment = Comment.deleteById(id);
        if (!comment) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(comment).build();
    }

    @Override
    public Response findById(Long id) {
        Optional<Comment> comment = Comment.findByIdOptional(id);

        if (comment.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(comment.get()).build();
    }

    @Override
    public Response findAll() {
        List<Comment> comments = Comment.findAll().list();

        if(comments == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(comments).build();
    }
}
