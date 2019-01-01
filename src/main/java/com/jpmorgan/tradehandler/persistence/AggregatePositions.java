package com.jpmorgan.tradehandler.persistence;

import com.jpmorgan.tradehandler.common.Constant;
import com.jpmorgan.tradehandler.model.AggregatePosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AggregatePositions {
    private static ConcurrentMap<AggregatePosition.PrimaryKey, AggregatePosition> aggregatePositions = new ConcurrentHashMap<>();

    public static ConcurrentMap<AggregatePosition.PrimaryKey, AggregatePosition> store() {
        return aggregatePositions;
    }
    
    public static void display() {
        List<AggregatePosition> positions = new ArrayList<>(aggregatePositions.values());
        Collections.sort(positions, new AggregatePositionComparator());
        System.out.println(String.format(Constant.STRING_FORMAT_FOR_AGGREGATE_POSITION, "", "", "Account", "Instrument", "Quantity", "Trades"));
        for (AggregatePosition position : positions) {
            System.out.println(String.format(Constant.STRING_FORMAT_FOR_AGGREGATE_POSITION, "", "", position.getKey().getAccountNumber(),
                    position.getKey().getSecurityIdentifier(), position.getQuantity(), position.getTrades()));
        }
    }

    private static class AggregatePositionComparator implements Comparator<AggregatePosition> {

        @Override
        public int compare(AggregatePosition o1, AggregatePosition o2) {
            // First of all, order ACS by account number
            int accountNumberOrder = o1.getKey().getAccountNumber().compareTo(o2.getKey().getAccountNumber());
            if (accountNumberOrder != 0) {
                return accountNumberOrder;
            }

            // Then, order DESC by security identifier
            return o2.getKey().getSecurityIdentifier().compareTo(o1.getKey().getSecurityIdentifier());
        }
    }
}
