package com.jpmorgan.tradehandler;

import java.util.concurrent.BlockingQueue;

import com.jpmorgan.tradehandler.model.TradeEvent;
import com.jpmorgan.tradehandler.persistence.AggregatePositions;
import com.jpmorgan.tradehandler.processor.TradeEventListener;
import com.jpmorgan.tradehandler.processor.TradeEventHandler;

public class TradeEventConsumer implements Runnable {

	private BlockingQueue<TradeEvent> queue = null;
	private TradeEventListener eventHandler = new TradeEventHandler();
    public TradeEventConsumer(BlockingQueue<TradeEvent> queue) {
        this.queue = queue; 
    }
    
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			TradeEvent tradeEvent = queue.poll();
			if (tradeEvent != null) {
				eventHandler.process(tradeEvent);
				AggregatePositions.display();
			}
		}
	}

}
