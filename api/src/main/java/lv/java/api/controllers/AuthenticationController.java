package lv.java.api.controllers;

import an.awesome.pipelinr.Pipeline;
import lv.java.api.common.mapping.AuthenticationMapping;
import lv.java.application.authentication.commands.register.RegisterCommand;
import lv.java.application.authentication.queries.login.LoginQuery;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.contracts.authentication.AuthenticationResponse;
import lv.java.contracts.authentication.LoginRequest;
import lv.java.contracts.authentication.RegisterRequest;
import lv.java.domain.common.error.Result;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("auth")
public class AuthenticationController extends ApiController {

    private final Pipeline pipeline;

    public AuthenticationController(@Qualifier("commandHandlers") Pipeline pipeline) {
        this.pipeline = Objects.requireNonNull(pipeline);
    }

    @PostMapping("register")
    public Mono<ResponseEntity<AuthenticationResponse>> register(@RequestBody RegisterRequest registerRequest) {
        var command = new RegisterCommand(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.email(),
                registerRequest.password()
        );

        Result<AuthenticationResult> registerResult = this.pipeline.send(command);

        return registerResult.match(
                a -> ok(AuthenticationMapping.mapToResponse(a)),
                this::problem);
    }

    @PostMapping("login")
    public Mono<ResponseEntity<AuthenticationResponse>> login(@RequestBody LoginRequest request) {
        var query = new LoginQuery(request.email(), request.password());
        Result<AuthenticationResult> loginResult = this.pipeline.send(query);

        return loginResult.match(
                a -> ok(AuthenticationMapping.mapToResponse(a)),
                this::problem);
    }
}
