package com.example.demoapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
class ErrorHandingTests {

    private static Flux<String> getFlux() {
        return Flux.just(1, 2, 0, 3)
                .map(i -> "100 / " + i + " = " + (100 / i));
    }

    @Test
    void monoHandlers() throws InterruptedException {
        getFlux()
                .onErrorReturn("Divided by zero :(")
                .subscribe(log::info);

        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
                .retry(1)
                .elapsed()
                .subscribe(System.out::println, System.err::println);

        Thread.sleep(2100);
    }

}
