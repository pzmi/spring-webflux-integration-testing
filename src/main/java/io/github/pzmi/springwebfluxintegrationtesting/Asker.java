package io.github.pzmi.springwebfluxintegrationtesting;

import reactor.core.publisher.Mono;

public class Asker {
    public Mono<String> askQuestion(String name) {
        return Mono.just(name + ", jak Ci na imie");
    }
}
