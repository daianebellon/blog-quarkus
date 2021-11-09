package br.com.daianebellon.startup;

import br.com.daianebellon.user.Usuario;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class Startup {

    @Transactional
    public void carregarUsuarios(@Observes StartupEvent evt) {
        Usuario.add("laura","email@email", "1234", "ADMIN");
        System.out.println(BcryptUtil.bcryptHash("email@email:1234"));
    }
}
