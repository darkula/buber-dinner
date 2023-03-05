package lv.java.application.authentication.commands.register;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.context.ValidationResult;
import lv.java.application.authentication.common.AuthenticationResult;
import lv.java.application.common.behaviors.CommandValidator;
import lv.java.domain.common.error.Error;
import lv.java.domain.common.error.Result;

import java.util.List;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static java.util.function.Predicate.not;

public class RegisterCommandValidator extends AbstractValidator<RegisterCommand> implements CommandValidator<Result<AuthenticationResult>, RegisterCommand> {

    @Override
    public Result<AuthenticationResult> executeValidation(RegisterCommand command) {
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
        ruleFor(RegisterCommand::firstName)
                .must(not(stringEmptyOrNull()))
                .withFieldName("firstName")
                .withMessage("First name must not be blank!");
        ruleFor(RegisterCommand::lastName)
                .must(not(stringEmptyOrNull()))
                .withFieldName("lastName")
                .withMessage("Lat name must not be blank!");
        ruleFor(RegisterCommand::email)
                .must(not(stringEmptyOrNull()))
                .withFieldName("email")
                .withMessage("Email must not be blank!");
        ruleFor(RegisterCommand::password)
                .must(not(stringEmptyOrNull()))
                .withFieldName("password")
                .withMessage("Password must not be blank!");
    }
}
