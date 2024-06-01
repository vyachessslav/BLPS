package gmail.vezhur2003.blps.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getById(Long id);

    UserEntity findByLogin(String login);
}