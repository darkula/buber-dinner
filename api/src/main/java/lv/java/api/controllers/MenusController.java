package lv.java.api.controllers;

import an.awesome.pipelinr.Pipeline;
import lv.java.contracts.menus.CreateMenuRequest;
import lv.java.contracts.menus.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static lv.java.api.common.mapping.MenusMapper.mapToCreateMenuCommand;
import static lv.java.api.common.mapping.MenusMapper.mapToMenuResponse;

@RestController
@RequestMapping("hosts")
public class MenusController extends ApiController {

    private final Pipeline pipeline;

    public MenusController(Pipeline pipeline) {
        this.pipeline = Objects.requireNonNull(pipeline);
    }

    @PostMapping("{hostId}/menus")
    public Mono<ResponseEntity<MenuResponse>> createMenu(@RequestBody CreateMenuRequest request, @PathVariable String hostId) {
        var command = mapToCreateMenuCommand(request, hostId);

        var createMenuResult = this.pipeline.send(command);
        return createMenuResult.match(
                a -> created(mapToMenuResponse(a)),
                this::problem);
    }

}
