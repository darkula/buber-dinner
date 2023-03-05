package lv.java.application.authentication.commands.register;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import lv.java.application.common.interfaces.persistance.UserRepository;
import lv.java.domain.common.error.Result;
import lv.java.domain.common.error.UserError;
import lv.java.domain.entities.User;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class RegisterCommandHandler implements Command.Handler<RegisterCommand, Result<AuthenticationResult>> {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserRepository userRepository;

    public RegisterCommandHandler(JwtTokenGenerator jwtTokenGenerator, UserRepository userRepository) {
        this.jwtTokenGenerator = Objects.requireNonNull(jwtTokenGenerator);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public Result<AuthenticationResult> handle(RegisterCommand command) {
        log.info("Handle RegisterCommand");
        Optional<User> userByEmail = this.userRepository.getUserByEmail(command.email());
        if (userByEmail.isPresent()) {
            return Result.error(UserError.DUPLICATE_EMAIL);
        }

        User user = new User(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.password());

        this.userRepository.add(user);

        String token = this.jwtTokenGenerator.generateToken(user);

        return Result.success(new AuthenticationResult(user, token));
    }
}
