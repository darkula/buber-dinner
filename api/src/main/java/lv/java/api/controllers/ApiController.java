package lv.java.api.controllers;

import lv.java.domain.common.error.Error;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public class ApiController {

    protected <T> Mono<ResponseEntity<T>> problem(List<Error> errors) {
        Error firstError = errors.get(0);

        int code = switch (firstError.getType()) {
            case CONFLICT -> 409;
            case VALIDATION -> 400;
            case NOT_FOUND -> 404;
            default -> 500;
        };

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(code), firstError.getDescription());

        if (!errors.isEmpty()) {
            problemDetail.setProperty("errorCodes", errors);
        }

        return Mono.just(ResponseEntity.of(problemDetail).build());
    }

    protected <T> Mono<ResponseEntity<T>> ok(T t) {
        return Mono.just(ResponseEntity.ok(t));
    }
}
