package com.jpmorgan.tradehandler.service;

import com.jpmorgan.tradehandler.dao.AggregatePositionDAO;
import com.jpmorgan.tradehandler.model.AggregatePosition;
import com.jpmorgan.tradehandler.model.AggregatePosition.PrimaryKey;

public class AggregatePositionServiceImpl implements AggregatePositionService {
	private AggregatePositionDAO aggregatePositionDAO = null;

	public AggregatePositionServiceImpl(AggregatePositionDAO aggregatePositionDAO) {
		this.aggregatePositionDAO = aggregatePositionDAO;
	}
	
	@Override
	public AggregatePosition addNewAggregatePosition(AggregatePosition aggregatePosition) {
		return aggregatePositionDAO.insert(aggregatePosition);
	}

	@Override
	public AggregatePosition updateExistingAggregatePosition(AggregatePosition aggregatePosition) {
		return aggregatePositionDAO.update(aggregatePosition);
	}

	@Override
	public boolean isExistingPosition(PrimaryKey key) {
		return aggregatePositionDAO.isExisting(key);
	}

	@Override
	public AggregatePosition getAggregatePositionOrNew(PrimaryKey key) {
		if(isExistingPosition(key)) {
			return aggregatePositionDAO.getByKey(key);
		}
		AggregatePosition newAggregatePosition = new AggregatePosition();
		newAggregatePosition.setKey(key);
		return newAggregatePosition;
	}

	@Override
	public AggregatePosition addNewOrUpdate(AggregatePosition aggregatePosition) {
		if (isExistingPosition(aggregatePosition.getKey())) {
			return updateExistingAggregatePosition(aggregatePosition);
		}
		
		return addNewAggregatePosition(aggregatePosition);
	}

}
