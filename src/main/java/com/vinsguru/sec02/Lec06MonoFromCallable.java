package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    To delay the execution using supplier / callable
 */
public class Lec06MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        
        // différence entre SUPPLIER et CALLABE :
        // le SUPPLIER ne peut pas envoyer des checked exceptions, que des runtime
        // CALLABLE a "throw Exception" dans la signature de sa méthode

        var list = List.of(1, 2, 3);
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.createSubscriber());

    }

    private static int sum(List<Integer> list) throws Exception {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }

}
