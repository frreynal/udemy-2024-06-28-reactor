package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec01.subscriber.SubscriberImpl;
import com.vinsguru.sec03.helper.NameGenerator;
import org.apache.commons.lang3.StringUtils;

public class Lec07FluxVsList {

    public static void main(String[] args) {

//        var list = NameGenerator.getNamesList(10);
//        System.out.println(list);

        var subscriber = new SubscriberImpl();
        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();

        NameGenerator.getNamesFlux(2)
            .subscribe(Util.createSubscriber("subGetNames"));

        NameGenerator.getNamesFlux(2)
            .subscribe(System.out::println);
    }

}
