package br.com.daianebellon;

import br.com.daianebellon.user.UsuarioResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
@TestHTTPEndpoint(UsuarioResource.class)
@TestSecurity(user = "email@email", roles = {"ADMIN"})
public class UsuarioResourceTest {

    @Test
    public void quandoProcurarPorIdRetornarUsuario() {
        given().contentType(ContentType.JSON)
                .pathParam("id", 1L)
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }


    @Test
    public void quandoProcurarPorIdRetornarNotFound() {
        given().contentType(ContentType.JSON)
                .pathParam("id", 34335353L)
                .when()
                .get("{id}")
                .then()
                .statusCode(404);
    }


    @Test
    public void quandoProcurarPorIdRetornar() {
        given().contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("email", hasItem("email@email"))
                .body("size()", greaterThanOrEqualTo(1));
    }
}
