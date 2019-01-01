package com.jpmorgan.tradehandler.service;

import com.jpmorgan.tradehandler.dao.DAOFactory;

public class ServiceFactory {
	private static AggregatePositionService aggregatePositionService = null;
	private static TradeEventService tradeEventService = null;
	
	public static AggregatePositionService instanceOfAggregatePositionService() {
		if (aggregatePositionService == null) {
			aggregatePositionService = new AggregatePositionServiceImpl(DAOFactory.instanceOfAggregatePositionDAO());
		}
		return aggregatePositionService;
	}
	
	public static TradeEventService instanceOfTradeEventService() {
		if (tradeEventService == null) {
			tradeEventService = new TradeEventServiceImpl(DAOFactory.instanceOfTradeEventDAO());
		}
		return tradeEventService;
	} 
}
