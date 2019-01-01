package com.jpmorgan.tradehandler;

import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.tradehandler.common.Direction;
import com.jpmorgan.tradehandler.common.Operation;
import com.jpmorgan.tradehandler.model.TradeEvent;

public class TradeEventGenerator {
	private static List<TradeEvent> tradeEvents = new ArrayList<>();

	static {
		tradeEvents.add(new TradeEvent(1234, 1, "XYZ", 100, Direction.BUY, "ACC-1234", Operation.NEW));
		tradeEvents.add(new TradeEvent(1234, 2, "XYZ", 150, Direction.BUY, "ACC-1234", Operation.AMEND));
		tradeEvents.add(new TradeEvent(5678, 1, "QED", 200, Direction.BUY, "ACC-2345", Operation.NEW));
		tradeEvents.add(new TradeEvent(5678, 2, "QED", 0, Direction.BUY, "ACC-2345", Operation.CANCEL));
		tradeEvents.add(new TradeEvent(2233, 1, "RET", 100, Direction.SELL, "ACC-3456", Operation.NEW));
		tradeEvents.add(new TradeEvent(2233, 2, "RET", 400, Direction.SELL, "ACC-3456", Operation.AMEND));
		tradeEvents.add(new TradeEvent(2233, 3, "RET", 0, Direction.SELL, "ACC-3456", Operation.CANCEL));
		tradeEvents.add(new TradeEvent(8896, 1, "YUI", 300, Direction.BUY, "ACC-4567", Operation.NEW));
		tradeEvents.add(new TradeEvent(6638, 1, "YUI", 100, Direction.SELL, "ACC-4567", Operation.NEW));
		tradeEvents.add(new TradeEvent(6363, 1, "HJK", 200, Direction.BUY, "ACC-5678", Operation.NEW));
		tradeEvents.add(new TradeEvent(7666, 1, "HJK", 200, Direction.BUY, "ACC-5678", Operation.NEW));
		tradeEvents.add(new TradeEvent(6363, 2, "HJK", 100, Direction.BUY, "ACC-5678", Operation.AMEND));
		tradeEvents.add(new TradeEvent(7666, 2, "HJK", 50, Direction.SELL, "ACC-5678", Operation.AMEND));
		tradeEvents.add(new TradeEvent(8686, 1, "FVB", 100, Direction.BUY, "ACC-6789", Operation.NEW));
		tradeEvents.add(new TradeEvent(8686, 2, "GBN", 100, Direction.BUY, "ACC-6789", Operation.AMEND));
		tradeEvents.add(new TradeEvent(9654, 1, "FVB", 200, Direction.BUY, "ACC-6789", Operation.NEW));
		tradeEvents.add(new TradeEvent(1025, 1, "JKL", 100, Direction.BUY, "ACC-7789", Operation.NEW));
		tradeEvents.add(new TradeEvent(1036, 1, "JKL", 100, Direction.BUY, "ACC-7789", Operation.NEW));
		tradeEvents.add(new TradeEvent(1025, 2, "JKL", 100, Direction.SELL, "ACC-8877", Operation.AMEND));
		tradeEvents.add(new TradeEvent(1122, 1, "KLO", 100, Direction.BUY, "ACC-9045", Operation.NEW));
		tradeEvents.add(new TradeEvent(1122, 2, "HJK", 100, Direction.SELL, "ACC-9045", Operation.AMEND));
		tradeEvents.add(new TradeEvent(1122, 3, "KLO", 100, Direction.SELL, "ACC-9045", Operation.AMEND));
		tradeEvents.add(new TradeEvent(1144, 1, "KLO", 300, Direction.BUY, "ACC-9045", Operation.NEW));
		tradeEvents.add(new TradeEvent(1144, 2, "KLO", 400, Direction.BUY, "ACC-9045", Operation.AMEND));
		tradeEvents.add(new TradeEvent(1155, 1, "KLO", 600, Direction.SELL, "ACC-9045", Operation.NEW));
		tradeEvents.add(new TradeEvent(1155, 2, "KLO", 0, Direction.BUY, "ACC-9045", Operation.CANCEL));
	}

	public static List<TradeEvent> store() {
		return tradeEvents;
	}
}
