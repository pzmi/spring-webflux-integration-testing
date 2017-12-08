package io.github.pzmi.springwebfluxintegrationtesting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    Asker asker() {
        return new Asker();
    }
}
