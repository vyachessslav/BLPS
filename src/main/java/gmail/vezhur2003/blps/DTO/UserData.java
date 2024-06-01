package gmail.vezhur2003.blps.DTO;

import gmail.vezhur2003.blps.security.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import gmail.vezhur2003.blps.primary.UserEntity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserData {
    private Long id;
    private String login;
    private Role role;

    public UserData(UserEntity u) {
        this.setId(u.getId());
        this.setLogin(u.getLogin());
        this.setRole(u.getRole());
    }
}
