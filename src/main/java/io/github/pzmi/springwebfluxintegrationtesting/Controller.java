package io.github.pzmi.springwebfluxintegrationtesting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/")
public class Controller {
    private Asker asker;

    public Controller(Asker asker) {
        this.asker = asker;
    }

    @GetMapping(value = "/{name}", produces = "text/plain;charset=UTF-8")
    public Mono<String> get(@PathVariable String name) {
        return asker.askQuestion(name);
    }
}
