package lv.java.api.config;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import lv.java.application.authentication.commands.register.RegisterCommandHandler;
import lv.java.application.authentication.commands.register.RegisterCommandValidator;
import lv.java.application.authentication.queries.login.LoginQueryHandler;
import lv.java.application.authentication.queries.login.LoginQueryValidator;
import lv.java.application.common.behaviors.ValidationBehavior;
import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import lv.java.application.common.interfaces.persistance.MenuRepository;
import lv.java.application.common.interfaces.persistance.UserRepository;
import lv.java.application.menus.commands.create_menu.CreateMenuCommandHandler;
import lv.java.application.menus.commands.create_menu.CreateMenuCommandValidator;
import lv.java.application.menus.events.MenuCreatedEventHandler;
import lv.java.infrastructure.authentication.JwtSettings;
import lv.java.infrastructure.authentication.JwtTokenGeneratorImpl;
import lv.java.infrastructure.persistence.repositories.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class InfraConfig {

    @Bean
    public JwtSettings initJwtSettings(
            @Value("${jwt-settings.secret}") String secret,
            @Value("${jwt-settings.expiry-minutes}") int expiryMinutes,
            @Value("${jwt-settings.issuer}") String issuer,
            @Value("${jwt-settings.audience}") String audience) {
        return new JwtSettings(
                secret,
                expiryMinutes,
                issuer,
                audience);
    }

    @Bean
    public UserRepository initUserRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public Pipeline commandHandlers(JwtTokenGenerator jwtTokenGenerator, UserRepository userRepository, MenuRepository menuRepository) {

        return new Pipelinr()
                .with(() -> Stream.of(
                        new RegisterCommandHandler(jwtTokenGenerator, userRepository),
                        new LoginQueryHandler(jwtTokenGenerator, userRepository),
                        new CreateMenuCommandHandler(menuRepository)))
                .with(() -> Stream.of(new ValidationBehavior(List.of(
                        new RegisterCommandValidator(),
                        new LoginQueryValidator(),
                        new CreateMenuCommandValidator()))));
    }

    @Bean
    public Pipeline notificationHandlers() {
        return new Pipelinr()
                .with(() -> Stream.of(new MenuCreatedEventHandler()));
    }

    @Bean
    public JwtTokenGenerator jwtTokenGenerator(JwtSettings jwtSettings) {
        return new JwtTokenGeneratorImpl(Clock.systemDefaultZone(), jwtSettings);
    }
}
