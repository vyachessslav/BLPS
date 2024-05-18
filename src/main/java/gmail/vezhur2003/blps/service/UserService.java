package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.RegistrationData;
import gmail.vezhur2003.blps.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gmail.vezhur2003.blps.DTO.UserLoginContext;
import gmail.vezhur2003.blps.entity.UserEntity;
import gmail.vezhur2003.blps.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserLoginContext registerEmployee(RegistrationData registrationData) {
        return new UserLoginContext(userRepository.save(new UserEntity(registrationData, Role.EMPLOYEE)));
    }

    public UserLoginContext registerEmployer(RegistrationData registrationData) {
        return new UserLoginContext(userRepository.save(new UserEntity(registrationData, Role.EMPLOYER)));
    }

    public UserLoginContext registerAdmin(RegistrationData registrationData) {
        return new UserLoginContext(userRepository.save(new UserEntity(registrationData, Role.ADMIN)));
    }
}
