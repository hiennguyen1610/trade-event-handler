package com.jpmorgan.tradehandler.dao;

import com.jpmorgan.tradehandler.model.AggregatePosition;

import java.util.concurrent.ConcurrentMap;

public interface AggregatePositionDAO {
	public AggregatePosition insert(AggregatePosition aggregatePosition);

	public AggregatePosition update(AggregatePosition aggregatePosition);
	
	public boolean isExisting(AggregatePosition.PrimaryKey key);
	
	public AggregatePosition getByKey(AggregatePosition.PrimaryKey key);

	public ConcurrentMap<AggregatePosition.PrimaryKey, AggregatePosition> getAggregatePositions();
}
