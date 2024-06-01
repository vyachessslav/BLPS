package gmail.vezhur2003.blps.security;

import gmail.vezhur2003.blps.primary.UserEntity;
import gmail.vezhur2003.blps.primary.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import javax.ws.rs.NotFoundException;
import java.security.Principal;
import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public class JaasAuthorityGranter implements AuthorityGranter {

    private final UserRepository userRepository;
    @Override
    public Set<String> grant(Principal principal) {
        UserEntity user = userRepository.findByLogin(principal.getName());
        if (user != null) {
            return Collections.singleton(user.getRole().name());
        } else {
            throw new NotFoundException();
        }
    }
}
