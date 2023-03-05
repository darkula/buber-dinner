package lv.java.application.authentication.queries.login;

import an.awesome.pipelinr.Command;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.domain.common.error.Result;

public record LoginQuery(String email,
                         String password) implements Command<Result<AuthenticationResult>> {
}
