package com.jpmorgan.tradehandler.processor;

import com.jpmorgan.tradehandler.common.Direction;
import com.jpmorgan.tradehandler.common.Operation;
import com.jpmorgan.tradehandler.model.AggregatePosition;
import com.jpmorgan.tradehandler.model.TradeEvent;
import com.jpmorgan.tradehandler.persistence.AggregatePositions;
import com.jpmorgan.tradehandler.persistence.TradeEvents;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nvh161084 on 4/25/2018.
 */
public class NewSellProcessorTest {
    private TradeEventProcessor processor = ProcessorFactory.processor(Operation.NEW, Direction.SELL);
    private AggregatePosition reversedAggregatePosition = null;
    private TradeEvent reversedTradeEvent = null;

    @Before
    public void setUp() throws Exception {
        AggregatePositions.store().clear();
        TradeEvents.store().clear();

        // By default: quantity = 0, trades = ""
        // For reverse(): incremented by 100 for reverse()
        // For apply(): quantity decreased by 100
        //              and 1111 presented in trades

        reversedAggregatePosition = new AggregatePosition();
        reversedAggregatePosition.getKey().setAccountNumber("ACC-9999");
        reversedAggregatePosition.getKey().setSecurityIdentifier("AAA");

        reversedTradeEvent = new TradeEvent(1111, 1, "AAA", 100, Direction.SELL, "ACC-9999", Operation.NEW);
    }

    @Test(expected = NullPointerException.class)
    public void reverse_WithNullOfAggregatePosition() throws Exception {
        processor.reverse(null, new TradeEvent());
    }

    @Test(expected = NullPointerException.class)
    public void reverse_WithNullOfTradeEvent() throws Exception {
        processor.reverse(new AggregatePosition(), null);
    }

    @Test
    public void reverse_QuantityCheck() throws Exception {
        AggregatePosition aggregatePosition = processor.reverse(reversedAggregatePosition, reversedTradeEvent);
        assertEquals(100, aggregatePosition.getQuantity());
        aggregatePosition = processor.reverse(reversedAggregatePosition, reversedTradeEvent);
        assertEquals(200, aggregatePosition.getQuantity());
    }

    @Test
    public void reverse_TradesCheck() throws Exception {
        AggregatePosition aggregatePosition = processor.reverse(reversedAggregatePosition, reversedTradeEvent);
        assertEquals("", aggregatePosition.getTrades());
    }

    @Test(expected = NullPointerException.class)
    public void apply_WithNullOfAggregatePosition() throws Exception {
        processor.apply(null, new TradeEvent());
    }

    @Test(expected = NullPointerException.class)
    public void apply_WithNullOfTradeEvent() throws Exception {
        processor.apply(new AggregatePosition(), null);
    }

    @Test
    public void apply_QuantityCheck() throws Exception {
        AggregatePosition aggregatePosition = processor.apply(reversedAggregatePosition, reversedTradeEvent);
        assertEquals(-100, aggregatePosition.getQuantity());
        aggregatePosition = processor.apply(reversedAggregatePosition, reversedTradeEvent);
        assertEquals(-200, aggregatePosition.getQuantity());
    }

    @Test
    public void apply_TradesCheckForOriginalEmpty() throws Exception {
        AggregatePosition aggregatePosition = processor.apply(reversedAggregatePosition, reversedTradeEvent);
        assertEquals("1111", aggregatePosition.getTrades());
    }

    @Test
    public void apply_TradesCheckForOriginalNotEmpty() throws Exception {
        reversedAggregatePosition.setTrades("1110");
        AggregatePosition aggregatePosition = processor.apply(reversedAggregatePosition, reversedTradeEvent);
        assertEquals("1110, 1111", aggregatePosition.getTrades());
        aggregatePosition = processor.apply(reversedAggregatePosition, reversedTradeEvent);
        assertEquals("1110, 1111", aggregatePosition.getTrades());
    }

}