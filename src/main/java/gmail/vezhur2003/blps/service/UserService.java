package gmail.vezhur2003.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gmail.vezhur2003.blps.DTO.UserLoginContext;
import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserLoginContext login(String login, String password) {
        UserEntity u = userRepository.findByLoginAndPassword(login, password);
        if (u == null) {
            throw new IllegalArgumentException("invalid username or password");
        } else {
            return new UserLoginContext(u);
        }
    }

    public UserLoginContext register(UserEntity user) {
        
        if (user.getLogin() == null || user.getRole() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Login, nickname, and password are required for registration");
        }
        if (!(user.getRole().equals("employer") || user.getRole().equals("employee"))) {
            throw new IllegalArgumentException("Invalid role");
        }
        if (user.getLogin().length() > 255) {
            throw new IllegalArgumentException("Login cannot be longer than 255 symbols");
        }
        if (user.getPassword().length() > 255) {
            throw new IllegalArgumentException("Password cannot be longer than 255 symbols");
        }
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("Login is already taken");
        }
        
        return new UserLoginContext(userRepository.save(user));
    }
}

