package lv.java.application.common.behaviors;

import an.awesome.pipelinr.Command;
import lv.java.domain.common.error.Result;

import java.util.List;
import java.util.Optional;

public class ValidationBehavior implements Command.Middleware {
    private final List<CommandValidator> validators;

    public ValidationBehavior(List<CommandValidator> validators) {
        this.validators = validators;
    }

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        Optional<CommandValidator> first = validators.stream().filter(v -> v.matches(command)).findFirst();
        if (first.isPresent()) {
            Result<?> validate = (Result<?>) first.get().executeValidation(command);
            if (validate.isError()) {
                return (R) validate;
            }
        }
        return next.invoke();
    }
}