package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/*
    If you have a CompletableFuture already, then we can convert that into a Mono
 */
public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {

        // ATTENTIOn si on met directement fromFuture(getName()), la méthode sera exécutée tout de suite même si on ne subsribe pas !!!
        // A NE PAS FAIRE :
//        Mono.fromFuture( getName());
        
        Mono.fromFuture(() -> getName())
                .subscribe(Util.createSubscriber());

        // mis pour bloquer le thread et provoquer la fin de la FUture
        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name");
            return Util.faker().name().lastName();
        });
    }

}
