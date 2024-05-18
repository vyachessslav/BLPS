package gmail.vezhur2003.blps.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    EMPLOYER("EMPLOYER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

}

