package lv.java.api.config;

import lv.java.application.common.interfaces.authentication.JwtTokenGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class ServerHttpBearerAuthenticationConverter implements ServerAuthenticationConverter {
    private static final String BEARER = "Bearer ";
    private static final Predicate<String> MATCH_BEARER_LENGTH = authValue -> authValue.length() > BEARER.length();
    private static final Function<String, Mono<String>> ISOLATE_BEARER_VALUE = authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));
    private final JwtTokenGenerator jwtTokenGenerator;

    public ServerHttpBearerAuthenticationConverter(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = Objects.requireNonNull(jwtTokenGenerator);
    }

    public static Mono<String> extract(ServerWebExchange serverWebExchange) {
        return Mono.justOrEmpty(serverWebExchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION));
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
                .flatMap(ServerHttpBearerAuthenticationConverter::extract)
                .filter(MATCH_BEARER_LENGTH)
                .flatMap(ISOLATE_BEARER_VALUE)
                .filter(jwtTokenGenerator::isTokenValid)
                .flatMap(token -> Mono.just(new UsernamePasswordAuthenticationToken("unknown", token)));
    }
}