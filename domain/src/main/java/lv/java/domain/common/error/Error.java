package lv.java.domain.common.error;

import java.util.Objects;

public class Error {
    private final String code;
    private final String description;
    private final ErrorType type;

    private int numericType;

    public Error(String code, String description, ErrorType type) {
        this.code = code;
        this.description = description;
        this.type = type;
    }

    public static Error failure(String code, String description) {

        return new Error(
                Objects.isNull(code) ? "General.Failure" : code,
                Objects.isNull(description) ? "A failure has occurred." : description,
                ErrorType.FAILURE
        );
    }

    public static Error unexpected(String code, String description) {

        return new Error(
                Objects.isNull(code) ? "General.Unexpected" : code,
                Objects.isNull(description) ? "An unexpected error has occurred." : description,
                ErrorType.UNEXPECTED
        );
    }

    public static Error validation(String code, String description) {

        return new Error(
                Objects.isNull(code) ? "General.Validation" : code,
                Objects.isNull(description) ? "A validation error has occurred." : description,
                ErrorType.VALIDATION
        );
    }

    public static Error conflict(String code, String description) {

        return new Error(
                Objects.isNull(code) ? "General.Conflict" : code,
                Objects.isNull(description) ? "A conflict error has occurred." : description,
                ErrorType.CONFLICT
        );
    }

    public static Error notFound(String code, String description) {

        return new Error(
                Objects.isNull(code) ? "General.NotFound" : code,
                Objects.isNull(description) ? "A 'Not Found' error has occurred." : description,
                ErrorType.NOT_FOUND
        );
    }

    public static Error custom(ErrorType type, String code, String description) {
        return new Error(code, description, type);
    }

    public String getDescription() {
        return this.description;
    }

    public ErrorType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}
