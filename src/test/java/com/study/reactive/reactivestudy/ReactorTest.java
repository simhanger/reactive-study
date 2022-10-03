package com.study.reactive.reactivestudy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Project : reactive-study
 * Author : simhanger
 * Date : 2022/09/10
 */
@Slf4j
public class ReactorTest {

    @Test
    public void test() {
        Flux<String> fruitFlux = Flux.just("바나나", "딸기", "토마토", "사과");
        fruitFlux.subscribe(
                f -> log.debug("과일들 = {}", f)
        );

        StepVerifier.create(fruitFlux)
                .expectNext("바나나")
                .expectNext("딸기")
                .expectNext("토마토")
                //.expectNext("사과")
                .verifyComplete();
    }

    @Test
    public void createFluxFromArray() {
        String[] fruits = {"apple", "banana", "durian"};
        Flux<String> flux = Flux.fromArray(fruits);
        flux.subscribe(f -> log.debug("Fruits = {}", f));

        Stream<String> fruitStream = Arrays.stream(fruits).distinct();
        Flux<String> flux1 = Flux.fromStream(fruitStream);
        flux1.subscribe(f -> log.debug("StreamFruit = {}", f));
    }

    @Test
    public void rangeFlux() {
        Flux<Integer> rangeFlux = Flux.range(1, 10);
        rangeFlux.subscribe(f -> log.debug("range = {}", f));
    }

    @Test
    public void intervalFlux() {
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5);
        intervalFlux.subscribe(f -> log.debug("interval = {}", f));

        // 이상하다.. 아래 검증하는코드가 없으면 위에 로그가 안찍히고 그냥 끝남 ㅡ,.ㅡ;;;;; 왜 그럴까??
        StepVerifier.create(intervalFlux)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L)
                //  .expectNext(5L)
                .verifyComplete();
    }

    @Test
    public void mergeFlex() {
        Flux<String> chracterFlex = Flux.just("adam", "superman", "eve")
                .delayElements(Duration.ofSeconds(3));

        Flux<String> foodFlux = Flux.just("bab", "water", "pizza")
                .delayElements(Duration.ofSeconds(3, 100));

        Flux<String> mergeFlus = chracterFlex.mergeWith(foodFlux);
        mergeFlus.subscribe(f -> log.debug("flux -> {}", f));


    }
}
