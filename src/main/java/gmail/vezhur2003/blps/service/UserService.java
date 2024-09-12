package gmail.vezhur2003.blps.service;

import gmail.vezhur2003.blps.DTO.RegistrationData;
import gmail.vezhur2003.blps.DTO.UserData;
import gmail.vezhur2003.blps.DTO.UserLoginContext;
import gmail.vezhur2003.blps.primary.UserEntity;
import gmail.vezhur2003.blps.primary.UserRepository;
import gmail.vezhur2003.blps.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("primaryTransactionManager")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public UserLoginContext registerEmployee(RegistrationData registrationData) {
        UserEntity userEntity = userRepository.save(new UserEntity(registrationData, Role.EMPLOYEE));
        kafkaProducerService.sendUser(userEntity.getLogin(), new UserData(userEntity));
        return new UserLoginContext(userEntity);
    }

    public UserLoginContext registerEmployer(RegistrationData registrationData) {
        return new UserLoginContext(userRepository.save(new UserEntity(registrationData, Role.EMPLOYER)));
    }

    public UserLoginContext registerAdmin(RegistrationData registrationData) {
        return new UserLoginContext(userRepository.save(new UserEntity(registrationData, Role.ADMIN)));
    }
}
