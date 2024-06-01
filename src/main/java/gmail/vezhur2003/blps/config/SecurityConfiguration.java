package gmail.vezhur2003.blps.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AbstractJaasAuthenticationProvider jaasAuthenticationProvider;

    @Value(value = "${api.endpoints.base-url}")
    private String baseUrl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authenticationProvider(jaasAuthenticationProvider)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(baseUrl + "/vacancy/unconfirmed").hasAuthority("ADMIN")
                        .requestMatchers(baseUrl + "/vacancy/confirmation").hasAuthority("ADMIN")
                        .requestMatchers(baseUrl + "/vacancy/creating").hasAuthority("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE,baseUrl + "/vacancy/*").hasAuthority("EMPLOYER")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
