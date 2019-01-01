package com.jpmorgan.tradehandler.dao;

import java.util.concurrent.ConcurrentMap;

import com.jpmorgan.tradehandler.model.AggregatePosition;
import com.jpmorgan.tradehandler.model.AggregatePosition.PrimaryKey;

public class AggregatePositionDAOImpl implements AggregatePositionDAO {
	private ConcurrentMap<AggregatePosition.PrimaryKey, AggregatePosition> aggregatePositions = null;

	AggregatePositionDAOImpl(ConcurrentMap<AggregatePosition.PrimaryKey, AggregatePosition> aggregatePositions) {
		this.aggregatePositions = aggregatePositions;
	}

	@Override
	public AggregatePosition insert(AggregatePosition aggregatePosition) {
		aggregatePositions.put(aggregatePosition.getKey(), aggregatePosition);
		return aggregatePosition;
	}

	@Override
	public AggregatePosition update(AggregatePosition aggregatePosition) {
		aggregatePositions.replace(aggregatePosition.getKey(), aggregatePosition);
		return aggregatePosition;
	}

	@Override
	public boolean isExisting(PrimaryKey key) {
		return aggregatePositions.containsKey(key);
	}

	@Override
	public AggregatePosition getByKey(PrimaryKey key) {
		return aggregatePositions.get(key);
	}

	public ConcurrentMap<PrimaryKey, AggregatePosition> getAggregatePositions() {
		return aggregatePositions;
	}
}
