package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

/*
    Flux generate
    - invokes the given lambda expression again and again based on downstream demand.
    - We can emit only one value at a time
    - will stop when complete method is invoked
    - will stop when error method is invoked
    - will stop downstream cancels
 */
public class Lec06FluxGenerate {
	
	private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);
	
	public static void main(String[] args) {
		
		// repasse en bocle dans la lambda expression, mais on ne peut appeler next qu'une seule fois
		Flux.generate(synchronousSink -> {
					log.info("invoked");
					var random = RandomUtils.nextInt();
					synchronousSink.next(random);
					// synchronousSink.next(2);
					//synchronousSink.complete();
//					synchronousSink.error(new RuntimeException("oops"));
				})
				.take(40)
				.subscribe(Util.createSubscriber());
		
		
	}
	
}
