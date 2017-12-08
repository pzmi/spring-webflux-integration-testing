package io.github.pzmi.springwebfluxintegrationtesting;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(Controller.class)
public class ControllerTest {

    @Autowired
    private WebTestClient web;

    @MockBean
    private Asker asker;

    @Test
    public void shouldAddNameToTheGreeting() throws Exception {
        when(asker.askQuestion(any())).thenReturn(Mono.just("Hello"));
        web.get().uri("/{name}", "andrzeju")
            .header("Content-Type", MediaType.TEXT_PLAIN_VALUE)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().valueEquals("Content-Type", "text/plain;charset=UTF-8")
            .expectBody(String.class)
            .isEqualTo("Hello");
    }

    @Test
    public void shouldReturnNotFoundWhenNotRequestingRootPath() throws Exception {
        web.get().uri("/aaa/bbb/ccc/ddd")
            .header("Content-Type", MediaType.TEXT_PLAIN_VALUE)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    @Disabled
    public void shouldFail() throws Exception {
        web.get().uri("/aaa/bbb/ccc/ddd")
            .header("Content-Type", MediaType.TEXT_PLAIN_VALUE)
            .exchange()
            .expectStatus().isUnauthorized();
    }
}