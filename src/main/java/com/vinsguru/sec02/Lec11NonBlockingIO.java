package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import com.vinsguru.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    To demo non-blocking IO
    Ensure that the external service is up and running!
 */
public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        log.info("starting");

        for (int i = 1; i <= 5; i++) {
            log.info("requested product-" + i);
            client.getProductName(i)
                    .subscribe(Util.createSubscriber("nonBlockingIO"));
        }
        Util.sleepSeconds(1);
        
        for (int i = 1; i <= 5; i++) {
            log.info("requested product-" + i);
            client.getProductName(i)
//                .subscribe(s -> log.info("received in subscription : " + s)); // pas de log, pourquoi ?
                .subscribe(p -> log.info("received in subscription : {}", p), // on en a seulement 1 ...
                    err -> log.error("error", err),
                    () -> log.info("completed"),
                    subscription -> subscription.request(Long.MAX_VALUE));
            client.getProductName(i).doOnNext(p -> log.info("received onNext : {}", p));
        }
        Util.sleepSeconds(1);
        
    }

}
