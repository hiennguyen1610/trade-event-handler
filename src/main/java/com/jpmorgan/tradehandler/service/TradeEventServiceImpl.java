package com.jpmorgan.tradehandler.service;

import java.util.List;

import com.jpmorgan.tradehandler.dao.TradeEventDAO;
import com.jpmorgan.tradehandler.model.TradeEvent;

public class TradeEventServiceImpl implements TradeEventService {

	private TradeEventDAO tradeEventDAO = null;

	public TradeEventServiceImpl(TradeEventDAO tradeEventDAO) {
		this.tradeEventDAO = tradeEventDAO;
	}

	@Override
	public TradeEvent getPreviousTradeEventOrNull(Integer tradeId) {
		List<TradeEvent> tradeEventList = tradeEventDAO.getTradeEventsByTradeId(tradeId);
		TradeEvent previousTradeEvent = null;
		if (!tradeEventList.isEmpty()) {
			previousTradeEvent = tradeEventList.get(tradeEventList.size() - 1);
		}
		return previousTradeEvent;
	}

	@Override
	public List<TradeEvent> addNew(TradeEvent tradeEvent) {
		return tradeEventDAO.insert(tradeEvent);
	}

}
