package br.com.daianebellon.user.impl;

import br.com.daianebellon.user.Usuario;
import br.com.daianebellon.user.UsuarioDocument;
import br.com.daianebellon.user.UsuarioService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
@Named("usuarioMongoService")
public class UsuarioMongoService implements UsuarioService {

    @Inject
    MongoClient mongoClient;

    @Override
    public Response register(Usuario usuario) {
        Document document = new Document()
            .append("nome", usuario.nome)
            .append("email", usuario.email)
            .append("senha", usuario.senha)
                .append("data", LocalDateTime.now())
                .append("tipo", "INSERIDO");

        InsertOneResult insertOneResult = getCollection().insertOne(document);

        return Response.ok(insertOneResult).build();
    }

    private Optional<Document> findDocumentById(String id) {
        MongoCursor<Document> iterator =
                getCollection().find(eq(new ObjectId(id))).iterator();
        return iterator.hasNext() ?
                Optional.of(iterator.next()) : Optional.empty();
    }

    @Override
    public Response deleteDocumentId(String id) {
        return Response.ok(getCollection().deleteOne(new Document("_id", id))).build();
    }

    @Override
    public Response findAll() {

        List<UsuarioDocument> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                UsuarioDocument usuarioDocument = new UsuarioDocument();
                usuarioDocument.setNome(document.getString("nome"));
                usuarioDocument.setEmail(document.getString("email"));
                list.add(usuarioDocument);
            }
        } finally {
            cursor.close();
        }

        return Response.ok(list).build();
    }

    @Override
    public Response findDocumentId(String id) {
        Optional<Document> doc = findDocumentById(id);

        if (doc.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(doc).build();
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("blog").getCollection("usuario");
    }

    @Override
    public Response findById(Long id) {
        return null;
    }

    @Override
    public Response deleteById(Long id) {
        return null;
    }
}
