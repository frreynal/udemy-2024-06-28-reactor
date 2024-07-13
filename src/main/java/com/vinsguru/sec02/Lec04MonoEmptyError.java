package com.vinsguru.sec02;

/*
    Emitting empty / error
 */

import com.vinsguru.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    public static void main(String[] args) {
        
        getUsername(1)
            .subscribe(Util.createSubscriber("s1"));
        
        getUsername(2)
            .subscribe(Util.createSubscriber("s2"));

        getUsername(3)
                .subscribe(Util.createSubscriber("s3"));
        
        // ATTENTION : dans ce cas, comme on ne fait rien avec l'erreur, on ne la verra même pas !!
        System.out.println("\n///////// aucun log d'erreur !!! //////////");
        getUsername(3)
            .subscribe(
                System.out::println,
                err-> {});
        
        System.out.println("\n///////// Par défaut, l'erreur est loguée //////////");
        
        getUsername(3)
            .subscribe(
                System.out::println);

    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); // null
            default -> Mono.error(new RuntimeException("invalid input !!"));
        };
    }

}
