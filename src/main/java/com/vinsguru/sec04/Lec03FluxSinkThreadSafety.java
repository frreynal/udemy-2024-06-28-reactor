package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import com.vinsguru.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/*
    FluxSink is thread safe!
    This is just a demo.
    It does NOT mean we should write code like this!
 */
public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {

//        demo1();
//        demo2();
        demo3();

    }

    // arraylist is not thread safe
    private static void demo1(){
        var list = new ArrayList<String>();
        Runnable runnable = () -> {
            for (int i = 0; i < 20; i++) {
                list.add( Thread.currentThread().getName() +"_" + i);
            }
        };
        for (int i = 0; i < 5; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(1);
        log.info("list size: {}", list.size());
        log.info("list : {}", list);
    }
    
    private static void demo2(){
        var list = new CopyOnWriteArrayList<>();
        Runnable runnable = () -> {
            for (int i = 0; i < 200; i++) {
                list.add( Thread.currentThread().getName() +"_" + i);
            }
        };
        for (int i = 0; i < 5; i++) {
            Thread.ofPlatform().start(runnable);
        }
        log.info("list size: {}", list.size());
        Util.sleep(Duration.ofNanos(1));
        log.info("list : {}", list);
    }

    // arraylist is not thread safe.
    // flux sink is thread safe. we get all the 10000 items safely into array list
    private static void demo3(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(s-> list.add(s));

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

}
