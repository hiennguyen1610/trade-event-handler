package com.jpmorgan.tradehandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.jpmorgan.tradehandler.model.TradeEvent;

public class TradedSecurityRunner {
	private static int MAX_OF_QUEUE =  1024;
	private static BlockingQueue<TradeEvent> tradeEventQueue = new ArrayBlockingQueue<TradeEvent>(MAX_OF_QUEUE); 
	public static void main(String[] args) {
		Thread producer = new Thread(new TradeEventProducer(tradeEventQueue));
		Thread consumer = new Thread(new TradeEventConsumer(tradeEventQueue));
		
		producer.start();
		consumer.start();
	}	
}
