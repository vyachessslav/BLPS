package gmail.vezhur2003.blps.security;

import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Map;

public class JaasLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;
    private String login;
    private boolean loginSucceeded = false;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = (UserRepository) options.get("userRepository");
    }

    @Override
    public boolean login()  {
        var nameCallback = new NameCallback("login: ");
        var passwordCallback = new PasswordCallback("password: ", false);
        try {
            callbackHandler.handle(new Callback[] {nameCallback, passwordCallback});
        } catch (IOException | UnsupportedCallbackException e) {
            throw new RuntimeException(e);
        }
        login = nameCallback.getName();
        String password = new String(passwordCallback.getPassword());
        UserEntity user = userRepository.findByLogin(login);
        if (user != null) {
            loginSucceeded = password.equals(user.getPassword());
        } else {
            return false;
        }
        return loginSucceeded;
    }

    @Override
    public boolean commit() {
        if(!loginSucceeded) return false;
        if(login == null) throw new NotFoundException();
        Principal principal = (UserPrincipal) () -> login;
        subject.getPrincipals().add(principal);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().removeIf(principal -> principal instanceof UserPrincipal);
        loginSucceeded = false;
        return true;
    }
}
