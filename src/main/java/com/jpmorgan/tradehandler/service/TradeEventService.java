package com.jpmorgan.tradehandler.service;

import java.util.List;

import com.jpmorgan.tradehandler.model.TradeEvent;

public interface TradeEventService {
	public TradeEvent getPreviousTradeEventOrNull(Integer tradeId);
	
	public List<TradeEvent> addNew(TradeEvent tradeEvent);
}
