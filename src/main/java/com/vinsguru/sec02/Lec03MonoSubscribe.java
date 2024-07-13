package com.vinsguru.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/*
    To discuss some of the subscribe overloaded methods
 */
public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {

        Mono<Integer> mono = Mono.just(1);
        
        mono.subscribe(
            i -> log.info("received: {}", i)
        );
        Mono<Integer> integerMono = mono.doOnNext(integer -> log.info(String.valueOf(integer + 1)));
        
        mono.subscribe(
                i -> log.info("received: {}", i),
                err -> log.error("error", err),
                () -> log.info("completed"),
                subscription -> subscription.request(2)
        );
        
        Mono<String> monoMap = Mono.just(1).map(i -> i + "a");
        monoMap.subscribe(
            s -> log.info("received: {}", s),
            err -> log.error("error : ", err),
            () -> log.info("completed")
        );
        
        var mono2 = Mono.just(1).map(i -> i /0 );
        mono2.subscribe(
            s -> log.info("received: {}", s),
            err -> log.error("error : {}", err.getMessage()),
            () -> log.info("completed")
        );
        
        integerMono.subscribe();

    }

}
