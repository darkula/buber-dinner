package lv.java.application.authentication.commands.register;

import an.awesome.pipelinr.Command;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.domain.common.error.Result;

public record RegisterCommand(String firstName,
                              String lastName,
                              String email,
                              String password) implements Command<Result<AuthenticationResult>> {
}
