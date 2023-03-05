package lv.java.application.authentication.queries.login;

import an.awesome.pipelinr.Command;
import lombok.extern.slf4j.Slf4j;
import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import lv.java.application.common.interfaces.persistance.UserRepository;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.domain.common.error.AuthenticationError;
import lv.java.domain.common.error.Result;
import lv.java.domain.user.User;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class LoginQueryHandler implements Command.Handler<LoginQuery, Result<AuthenticationResult>> {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserRepository userRepository;

    public LoginQueryHandler(JwtTokenGenerator jwtTokenGenerator, UserRepository userRepository) {
        this.jwtTokenGenerator = Objects.requireNonNull(jwtTokenGenerator);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public Result<AuthenticationResult> handle(LoginQuery query) {
        log.info("Handle LoginQuery");

        Optional<User> userByEmail = this.userRepository.getUserByEmail(query.email());
        if (userByEmail.isEmpty()) {
            return Result.error(AuthenticationError.INVALID_CREDENTIALS);
        }

        User user = userByEmail.get();

        if (!user.getPassword().equals(query.password())) {
            return Result.error(AuthenticationError.INVALID_CREDENTIALS);
        }

        String token = this.jwtTokenGenerator.generateToken(user);

        return Result.success(new AuthenticationResult(user, token));
    }
}
