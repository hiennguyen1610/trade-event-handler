package com.jpmorgan.tradehandler.processor;

import com.jpmorgan.tradehandler.exception.TradeEventException;
import com.jpmorgan.tradehandler.model.AggregatePosition;
import com.jpmorgan.tradehandler.model.TradeEvent;
import com.jpmorgan.tradehandler.service.AggregatePositionService;
import com.jpmorgan.tradehandler.service.TradeEventService;
import com.jpmorgan.tradehandler.utils.Logger;

public abstract class TradeEventProcessor {
    private AggregatePositionService aggregatePositionService = null;
    private TradeEventService tradeEventService = null;
    TradeEventProcessor(AggregatePositionService aggregatePositionService, TradeEventService tradeEventService) {
    	this.aggregatePositionService = aggregatePositionService;
    	this.tradeEventService = tradeEventService;
    }
    
    // Steps for handling a trade event
    public void processTradeEvent(TradeEvent currentTradeEvent) {
        Logger.log("Current Trade Event:", currentTradeEvent);
        AggregatePosition positionByCurrentTrade = getExistingPositionOrNew(currentTradeEvent.getAccountNumber(),
                currentTradeEvent.getSecurityIdentifier());
        Logger.log("Aggregate Position By Current:", positionByCurrentTrade);

        reverseIfPreviousTrade(currentTradeEvent, positionByCurrentTrade);
        apply(positionByCurrentTrade, currentTradeEvent);
        saveAggregatePosition(positionByCurrentTrade);
        saveTradeEvent(currentTradeEvent);
    }

    private void reverseIfPreviousTrade(TradeEvent currentTradeEvent, AggregatePosition positionByCurrent) {
        TradeEvent previousTradeEvent = getPreviousTradeEventOrNone(currentTradeEvent.getKey().getTradeId());
        Logger.log("Previous trade event:", previousTradeEvent);
        if (previousTradeEvent != null) {
        	if(currentTradeEvent.getKey().getTradeVersion() <= previousTradeEvent.getKey().getTradeVersion()) {
        		throw (new TradeEventException("Trade event ignored: The trade has been done with newer version."));
        	}
            boolean isSameAggregatePostion =
                    currentTradeEvent.getAccountNumber().equalsIgnoreCase(previousTradeEvent.getAccountNumber()) &&
                    currentTradeEvent.getSecurityIdentifier().equalsIgnoreCase(previousTradeEvent.getSecurityIdentifier());
            if (isSameAggregatePostion) {
                doReversePreviousTrade(positionByCurrent, previousTradeEvent);
            } else {
                AggregatePosition positionByPrevious = getExistingPositionOrNew(previousTradeEvent.getAccountNumber(),
                        previousTradeEvent.getSecurityIdentifier());
                Logger.log("Aggregate Position By Previous:", positionByPrevious);
                doReversePreviousTrade(positionByPrevious, previousTradeEvent);
                saveAggregatePosition(positionByPrevious);
            }
        }
    }

    private void doReversePreviousTrade(AggregatePosition aggregatePosition, TradeEvent previous) {
        TradeEventProcessor reverseProcessor =
                ProcessorFactory.processor(
                        previous.getOperation(), previous.getDirection());
        reverseProcessor.reverse(aggregatePosition, previous);
    }

    private AggregatePosition getExistingPositionOrNew(String accountNumber, String securityIdentifier) {
        AggregatePosition aggregatePosition = new AggregatePosition();
        AggregatePosition.PrimaryKey key = aggregatePosition.getKey();
        key.setAccountNumber(accountNumber);
        key.setSecurityIdentifier(securityIdentifier);

        return aggregatePositionService.getAggregatePositionOrNew(key);
    }

    private TradeEvent getPreviousTradeEventOrNone(Integer tradeId) {
        return tradeEventService.getPreviousTradeEventOrNull(tradeId);
    }

    private AggregatePosition saveAggregatePosition(AggregatePosition aggregatePosition) throws TradeEventException {
        return aggregatePositionService.addNewOrUpdate(aggregatePosition);
    }

    private void saveTradeEvent(TradeEvent tradeEvent) throws TradeEventException {
        tradeEventService.addNew(tradeEvent);
    }

    public abstract AggregatePosition reverse(AggregatePosition aggregatePosition, TradeEvent previousTradeEvent) throws TradeEventException;

    public abstract AggregatePosition apply(AggregatePosition aggregatePosition, TradeEvent currentTradeEvent) throws TradeEventException;

}
