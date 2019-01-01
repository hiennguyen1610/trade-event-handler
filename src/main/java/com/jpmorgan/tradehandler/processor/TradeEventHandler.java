package com.jpmorgan.tradehandler.processor;

import com.jpmorgan.tradehandler.exception.TradeEventException;
import com.jpmorgan.tradehandler.model.TradeEvent;
import com.jpmorgan.tradehandler.utils.Logger;;

public class TradeEventHandler implements TradeEventListener {
	
	public void process(TradeEvent tradeEvent) {
		try {
			TradeEventProcessor eventProcessor = 
					ProcessorFactory.processor(
							tradeEvent.getOperation(), tradeEvent.getDirection());
			eventProcessor.processTradeEvent(tradeEvent);
		} catch (TradeEventException ex) {
			Logger.log(ex.getMessage());
		}
	}
}
