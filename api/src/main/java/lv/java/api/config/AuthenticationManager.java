package lv.java.api.config;

import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import lv.java.application.common.interfaces.persistance.UserRepository;
import lv.java.domain.user.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserRepository userRepository;

    public AuthenticationManager(JwtTokenGenerator jwtTokenGenerator, UserRepository userRepository) {
        this.jwtTokenGenerator = Objects.requireNonNull(jwtTokenGenerator);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        if (!jwtTokenGenerator.isTokenValid(token)) return Mono.empty();

        String username = this.jwtTokenGenerator.getUserName(token);

        Optional<User> userByEmail = userRepository.getUserByEmail(username);

        if (userByEmail.isEmpty()) return Mono.error(new UsernameNotFoundException("Username Not Found Exception!"));

        User user = userByEmail.get();

        if (username.equalsIgnoreCase(user.getEmail())) {
            return Mono.just(new UsernamePasswordAuthenticationToken(username, token, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
        }

        return Mono.error(new BadCredentialsException("Bad Credentials Exception"));
    }
}
