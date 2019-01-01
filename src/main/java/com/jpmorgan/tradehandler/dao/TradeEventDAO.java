package com.jpmorgan.tradehandler.dao;

import java.util.List;

import com.jpmorgan.tradehandler.model.TradeEvent;

public interface TradeEventDAO {
	public List<TradeEvent> insert(TradeEvent tradeEvent);
	public List<TradeEvent> getTradeEventsByTradeId(Integer tradeId);
}
