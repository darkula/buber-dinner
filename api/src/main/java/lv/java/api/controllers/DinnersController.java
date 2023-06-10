package lv.java.api.controllers;

import an.awesome.pipelinr.Pipeline;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Objects;

@RestController
@RequestMapping("dinners")
public class DinnersController extends ApiController {

    private final Pipeline pipeline;

    public DinnersController(@Qualifier("commandHandlers") Pipeline pipeline) {
        this.pipeline = Objects.requireNonNull(pipeline);
    }

    @GetMapping
    public ResponseEntity<Flux<String>> dinners() {
        return ResponseEntity.ok(Flux.just("A", "B", "C"));
    }
}
