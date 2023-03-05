package lv.java.application.authentication.queries.login;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.context.ValidationResult;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.application.common.behaviors.CommandValidator;
import lv.java.domain.common.error.Error;
import lv.java.domain.common.error.Result;

import java.util.List;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static java.util.function.Predicate.not;

public class LoginQueryValidator extends AbstractValidator<LoginQuery> implements CommandValidator<Result<AuthenticationResult>, LoginQuery> {

    @Override
    public Result<AuthenticationResult> executeValidation(LoginQuery command) {
        ValidationResult validate = validate(command);
        if (validate.isValid()) {
            return Result.skip();
        }

        List<Error> errors = validate.getErrors()
                .stream()
                .map(e -> Error.validation(e.getField(), e.getMessage()))
                .toList();

        return Result.error(errors);
    }

    @Override
    public void rules() {
        ruleFor(LoginQuery::email)
                .must(not(stringEmptyOrNull()))
                .withFieldName("email")
                .withMessage("Email must not be blank!");
        ruleFor(LoginQuery::password)
                .must(not(stringEmptyOrNull()))
                .withFieldName("password")
                .withMessage("Password must not be blank!");
    }
}
