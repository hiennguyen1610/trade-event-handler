package com.jpmorgan.tradehandler.processor;

import com.jpmorgan.tradehandler.exception.TradeEventException;
import com.jpmorgan.tradehandler.model.TradeEvent;

public interface TradeEventListener {
	public void process(TradeEvent tradeEvent) throws TradeEventException;
}
