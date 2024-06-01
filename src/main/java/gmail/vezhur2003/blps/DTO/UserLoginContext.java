package gmail.vezhur2003.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gmail.vezhur2003.blps.primary.UserEntity;

@Getter
@Setter
@ToString
public class UserLoginContext {
    private boolean success;
    private gmail.vezhur2003.blps.DTO.UserData user;
    private String error;

    public UserLoginContext() {
        this.success = false;
        this.user = null;
        this.error = "empty login context";
    }

    public UserLoginContext(UserEntity user) {
        this.setSuccess(true);
        this.setUser(new gmail.vezhur2003.blps.DTO.UserData(user));
        this.setError("");
    }

    public UserLoginContext(String errorMessage) {
        this.success = false;
        this.user = null;
        this.error = errorMessage;
    }
}
