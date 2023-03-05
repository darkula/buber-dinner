package lv.java.domain.common.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Result<T> implements IErrorOr {
    private final T value;
    private final List<Error> errors = new ArrayList<>();

    private static final Error NO_ERROR = Error.unexpected("ErrorOr.NoErrors", "Error list cannot be retrieved from a successful ErrorOr.");

    private boolean isError;

    private Result() {
        this.value = null;
        this.isError = false;
    }

    private Result(Error error) {
        this.value = null;
        this.errors.add(error);
        this.isError = true;
    }

    private Result(List<Error> errors) {
        this.value = null;
        this.errors.addAll(errors);
        this.isError = true;
    }

    private Result(T value) {
        this.value = value;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value);
    }

    public static <T> Result<T> skip() {
        return new Result<>();
    }

    public static <T> Result<T> error(Error error) {
        return new Result<>(error);
    }

    public static <T> Result<T> error(List<Error> errors) {
        return new Result<>(errors);
    }

    public static <T> Result<T> error(Error[] errors) {
        return new Result<>(Arrays.asList(errors));
    }

    public Error firstError() {
        return this.errors.stream().findFirst().orElse(NO_ERROR);
    }

    public <R> R match(Function<T, R> onValue, Function<List<Error>, R> onError) {
        if (this.isError) {
            return onError.apply(this.errors);
        }

        return onValue.apply(this.value);
    }

    public <R> R matchFirst(Function<T, R> onValue, Function<Error, R> onFirstError) {
        if (this.isError) {
            return onFirstError.apply(firstError());
        }

        return onValue.apply(value);
    }

    @Override
    public List<Error> errors() {
        return this.errors;
    }

    @Override
    public boolean isError() {
        return this.isError;
    }

    public T getValue() {
        return this.value;
    }
}
