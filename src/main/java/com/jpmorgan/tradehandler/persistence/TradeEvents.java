package com.jpmorgan.tradehandler.persistence;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.jpmorgan.tradehandler.model.TradeEvent;

public class TradeEvents {
	// TradeId as a key
	private static ConcurrentMap<Integer, List<TradeEvent>> tradeEvents = new ConcurrentHashMap<>();

	public static ConcurrentMap<Integer, List<TradeEvent>> store() {
		return tradeEvents;
	}

}
