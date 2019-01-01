package com.jpmorgan.tradehandler.dao;

import com.jpmorgan.tradehandler.persistence.AggregatePositions;
import com.jpmorgan.tradehandler.persistence.TradeEvents;

public class DAOFactory {
	private static AggregatePositionDAO aggregatePositionDAOImpl = null;
	private static TradeEventDAO tradeEventDAO = null;
	
	public static AggregatePositionDAO instanceOfAggregatePositionDAO() {
		if (aggregatePositionDAOImpl == null) {
			aggregatePositionDAOImpl = new AggregatePositionDAOImpl(AggregatePositions.store());
		}
		return aggregatePositionDAOImpl;
	}
	
	public static TradeEventDAO instanceOfTradeEventDAO() {
		if (tradeEventDAO == null) {
			tradeEventDAO = new TradeEventDAOImpl(TradeEvents.store());
		}
		return tradeEventDAO;
	}
}
