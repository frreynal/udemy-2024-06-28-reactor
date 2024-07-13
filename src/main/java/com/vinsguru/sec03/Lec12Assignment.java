package com.vinsguru.sec03;


import com.vinsguru.common.Util;
import com.vinsguru.sec03.assignment.StockPriceObserver;
import com.vinsguru.sec03.client.ExternalServiceClient;

/*
    Ensure that the external service is up and running!
 */
public class Lec12Assignment {
	
	
	public static void main(String[] args) {
		
		var client = new ExternalServiceClient();
		var subscriber = new StockPriceObserver();
		client.getPriceChanges()
				.subscribe(subscriber);
		
		
		Util.sleepSeconds(20);
		
	}
	
//	mon exercice:

//	public static void main(String[] args) {
//		var client = new ExternalServiceClient();
//
//		client.getPriceChanges()
//				.subscribe(p -> {
//							if (p < 90) {
//								System.out.println("buy p : " + p);
//							} else {
//								System.out.println("sold p : " + p);
//							}
//						}
//				);
//
//		Util.sleepSeconds(6);
//
//	}




}
