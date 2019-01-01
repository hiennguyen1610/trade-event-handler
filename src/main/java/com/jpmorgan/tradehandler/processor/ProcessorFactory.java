package com.jpmorgan.tradehandler.processor;

import com.jpmorgan.tradehandler.common.Direction;
import com.jpmorgan.tradehandler.common.Operation;
import com.jpmorgan.tradehandler.exception.TradeEventException;
import com.jpmorgan.tradehandler.service.ServiceFactory;

public class ProcessorFactory {

	public static TradeEventProcessor processor(Operation operation, Direction direction) {
		TradeEventProcessor tradeEventProcessor = null;

		if (operation == Operation.NEW && direction == Direction.BUY) {
			tradeEventProcessor = new NewBuyProcessor(
					ServiceFactory.instanceOfAggregatePositionService(), 
					ServiceFactory.instanceOfTradeEventService());
		} else if (operation == Operation.NEW && direction == Direction.SELL) {
			tradeEventProcessor = new NewSellProcessor(
					ServiceFactory.instanceOfAggregatePositionService(), 
					ServiceFactory.instanceOfTradeEventService());
		} else if (operation == Operation.AMEND && direction == Direction.BUY) {
			tradeEventProcessor = new AmendBuyProcessor(
					ServiceFactory.instanceOfAggregatePositionService(), 
					ServiceFactory.instanceOfTradeEventService());
		} else if (operation == Operation.AMEND && direction == Direction.SELL) {
			tradeEventProcessor = new AmendSellProcessor(
					ServiceFactory.instanceOfAggregatePositionService(), 
					ServiceFactory.instanceOfTradeEventService());
		} else if (operation == Operation.CANCEL && direction == Direction.BUY) {
			tradeEventProcessor = new CancelBuyProcessor(
					ServiceFactory.instanceOfAggregatePositionService(), 
					ServiceFactory.instanceOfTradeEventService());
		} else if (operation == Operation.CANCEL && direction == Direction.SELL) {
			tradeEventProcessor = new CancelSellProcessor(
					ServiceFactory.instanceOfAggregatePositionService(), 
					ServiceFactory.instanceOfTradeEventService());
		} else {
			throw new TradeEventException("Unsupport trade event.");
		}
		return tradeEventProcessor;
	}
}
