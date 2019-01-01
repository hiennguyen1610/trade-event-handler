package com.jpmorgan.tradehandler.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.jpmorgan.tradehandler.model.TradeEvent;

public class TradeEventDAOImpl implements TradeEventDAO {
	private ConcurrentMap<Integer, List<TradeEvent>> tradeEvents = null;
	
	TradeEventDAOImpl(ConcurrentMap<Integer, List<TradeEvent>> tradeEvents) {
		this.tradeEvents = tradeEvents;
	}
	
	@Override
	public List<TradeEvent> insert(TradeEvent tradeEvent) {
		Integer tradeId = tradeEvent.getKey().getTradeId();
		if(tradeEvents.containsKey(tradeId)) {
			tradeEvents.get(tradeId).add(tradeEvent);
		} else {
			List<TradeEvent> tradeEventList = new ArrayList<>();
			tradeEventList.add(tradeEvent);
			tradeEvents.put(tradeId, tradeEventList);
		}
		
		return tradeEvents.get(tradeId);
	}
	
	@Override
	public List<TradeEvent> getTradeEventsByTradeId(Integer tradeId) {
		List<TradeEvent> tradeEventsById = tradeEvents.get(tradeId);
		if(tradeEventsById == null) tradeEventsById = new ArrayList<>();
		return tradeEventsById;
	}
}
