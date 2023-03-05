package lv.java.api.config;

import lombok.extern.slf4j.Slf4j;
import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import lv.java.application.common.interfaces.persistance.UserRepository;
import lv.java.domain.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public SecurityConfig(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.authenticationManager = Objects.requireNonNull(authenticationManager);
        this.jwtTokenGenerator = Objects.requireNonNull(jwtTokenGenerator);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ReactiveUserDetailsService userDetailsService(PasswordEncoder encoder) {
        return name -> {
            Optional<User> userByEmail = this.userRepository.getUserByEmail(name);
            return userByEmail
                    .map(user -> Mono.just(org.springframework.security.core.userdetails.User.builder()
                            .username(user.getEmail())
                            .password(encoder.encode(user.getPassword()))
                            .roles("ROLE_USER")
                            .build()))
                    .orElse(Mono.empty());
        };
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(
                        auth -> {
                            auth.pathMatchers("/auth/**").permitAll();
                            auth.anyExchange().authenticated();
                        })
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                    log.info("[1] Authentication error: Unauthorized[401]: {}", e.getMessage());
                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
                })
                .accessDeniedHandler((swe, e) -> {
                    log.info("[2] Authentication error: Access Denied[403]: {}", e.getMessage());
                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
                })
                .and()
                .addFilterAt(bearerAuthenticationFilter(authenticationManager), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    AuthenticationWebFilter bearerAuthenticationFilter(AuthenticationManager authManager) {
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authManager);
        bearerAuthenticationFilter.setServerAuthenticationConverter(new ServerHttpBearerAuthenticationConverter(jwtTokenGenerator));
        return bearerAuthenticationFilter;
    }

}