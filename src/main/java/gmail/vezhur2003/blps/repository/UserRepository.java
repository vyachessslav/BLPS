package gmail.vezhur2003.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gmail.vezhur2003.blps.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getById(Long id);

    UserEntity findByLogin(String login);
}