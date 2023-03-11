package lv.java.application.menus.commands.create_menu;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.context.ValidationResult;
import lv.java.application.common.behaviors.CommandValidator;
import lv.java.domain.common.error.Error;
import lv.java.domain.common.error.Result;
import lv.java.domain.menu.Menu;

import java.util.List;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static java.util.function.Predicate.not;

public class CreateMenuCommandValidator extends AbstractValidator<CreateMenuCommand> implements CommandValidator<Result<Menu>, CreateMenuCommand> {

    @Override
    public Result<Menu> executeValidation(CreateMenuCommand command) {
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
        ruleFor(CreateMenuCommand::name)
                .must(not(stringEmptyOrNull()))
                .withFieldName("name")
                .withMessage("Name must not be blank!");
        ruleFor(CreateMenuCommand::description)
                .must(not(stringEmptyOrNull()))
                .withFieldName("description")
                .withMessage("Description must not be blank!");
        ruleFor(CreateMenuCommand::sections)
                .must(not(List::isEmpty))
                .withFieldName("sections")
                .withMessage("Sections must not be blank!");
    }
}
