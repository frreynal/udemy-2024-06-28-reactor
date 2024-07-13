package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    To delay the execution using supplier / callable
 */
public class Lec05MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {

        var list = List.of(2, 2, 4);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.createSubscriber("subSuppliuer"));
        
        // use JUST only if the value is in MEMORY comme un string, un int
        // si la valeur provient d'une méthode, utiliser un SUPPLIER
        // là au moment de la compilation on n'a pas encore fait la somme (?) donc il vaut mieux utiliser un SUPPLIER
        Mono.just(sum(list))
            .subscribe(Util.createSubscriber("subJust"));
        
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }

}
