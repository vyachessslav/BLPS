package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(username);
        if (user == null) throw new UsernameNotFoundException("нет такого пользователя");
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
