package br.com.daianebellon.user;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@UserDefinition
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;

    @Username
    public String email;

    @Password
    public String senha;

    @Roles
    public String role;

    public static void add(String email, String senha, String role) {
        Usuario usuario = new Usuario();
        usuario.email = email;
        usuario.senha = BcryptUtil.bcryptHash(senha);
        usuario.role = role;
        usuario.persist();
    }

}