package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.client.ExternalServiceClient;
import com.vinsguru.sec03.helper.NameGenerator;

/*
    To demo non-blocking IO with streaming messages
    Ensure that the external service is up and running!
 */
public class Lec08NonBlockingStreamingMessages {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        client.getNames()
                .subscribe(Util.createSubscriber("sub1"));

        client.getNames()
                .subscribe(Util.createSubscriber("sub2"));
        
        client.getNames()
            .subscribe(s-> System.out.println("get names from client before sleep : " + s));
        
        System.out.println("now sleep 6");
        Util.sleepSeconds(6);
        System.out.println("end sleep 6");
        
        client.getNames()
            .subscribe(s-> System.out.println("get names from client : " + s)); // pas de log car pas de sleep
        

    }

}
