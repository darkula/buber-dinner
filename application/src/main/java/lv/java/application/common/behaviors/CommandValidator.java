package lv.java.application.common.behaviors;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.repack.com.google.common.reflect.TypeToken;

public interface CommandValidator<R, C extends Command<R>> {
    R executeValidation(C command);

    default boolean matches(C command) {
        TypeToken<C> typeToken = new TypeToken<C>(getClass()) {
        };

        return typeToken.isSupertypeOf(command.getClass());
    }
}