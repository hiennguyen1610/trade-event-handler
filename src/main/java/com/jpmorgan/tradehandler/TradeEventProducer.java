package com.jpmorgan.tradehandler;

import java.util.concurrent.BlockingQueue;

import com.jpmorgan.tradehandler.model.TradeEvent;
import com.jpmorgan.tradehandler.utils.ThreadUtils;

public class TradeEventProducer implements Runnable {

	private BlockingQueue<TradeEvent> queue = null;

	public TradeEventProducer(BlockingQueue<TradeEvent> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		for (TradeEvent tradeEvent : TradeEventGenerator.store()) {
			try {
				queue.put(tradeEvent);
				ThreadUtils.sleepForAWhile();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
