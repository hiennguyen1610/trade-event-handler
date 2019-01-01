package com.jpmorgan.tradehandler.service;

import com.jpmorgan.tradehandler.model.AggregatePosition;

public interface AggregatePositionService {
	public AggregatePosition addNewAggregatePosition(AggregatePosition aggregatePosition);

	public AggregatePosition updateExistingAggregatePosition(AggregatePosition aggregatePosition);
	
	public AggregatePosition addNewOrUpdate(AggregatePosition aggregatePosition);

	public AggregatePosition getAggregatePositionOrNew(AggregatePosition.PrimaryKey key);
	
	public boolean isExistingPosition(AggregatePosition.PrimaryKey key);
}
