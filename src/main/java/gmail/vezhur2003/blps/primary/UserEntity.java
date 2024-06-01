package gmail.vezhur2003.blps.primary;

import gmail.vezhur2003.blps.DTO.RegistrationData;
import gmail.vezhur2003.blps.security.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "account")
@Data
@ToString
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    public UserEntity(RegistrationData registrationData, Role role) {
        login = registrationData.getLogin();
        password = registrationData.getPassword();
        this.role = role;
    }
}
