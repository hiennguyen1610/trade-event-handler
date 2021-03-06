package com.jpmorgan.tradehandler.processor;

import com.jpmorgan.tradehandler.exception.TradeEventException;
import com.jpmorgan.tradehandler.model.AggregatePosition;
import com.jpmorgan.tradehandler.model.TradeEvent;
import com.jpmorgan.tradehandler.service.AggregatePositionService;
import com.jpmorgan.tradehandler.service.TradeEventService;

public class NewSellProcessor extends TradeEventProcessor {

	NewSellProcessor(AggregatePositionService aggregatePositionService, TradeEventService tradeEventService) {
		super(aggregatePositionService, tradeEventService);
	}

	@Override
	public AggregatePosition reverse(AggregatePosition aggregatePosition, TradeEvent previousTradeEvent)
			throws TradeEventException {
		aggregatePosition.setQuantity(aggregatePosition.getQuantity() + previousTradeEvent.getQuantity());
		return aggregatePosition;
	}

	@Override
	public AggregatePosition apply(AggregatePosition aggregatePosition, TradeEvent currentTradeEvent)
			throws TradeEventException {
		aggregatePosition.setQuantity(aggregatePosition.getQuantity() - currentTradeEvent.getQuantity());
		aggregatePosition.appendTradeIdToTrades(currentTradeEvent.getKey().getTradeId().toString());
		return aggregatePosition;
	}

}
