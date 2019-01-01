package com.jpmorgan.tradehandler.model;

import com.jpmorgan.tradehandler.common.Constant;
import com.jpmorgan.tradehandler.common.Direction;
import com.jpmorgan.tradehandler.common.Operation;

public class TradeEvent {
    private PrimaryKey key;
    private String accountNumber;
    private String securityIdentifier;
    private int quantity;
    private Direction direction;
    private Operation operation;

    public TradeEvent() {
        super();
    }

    public TradeEvent(Integer tradeId, int tradeVersion, String securityIdentifier, int quantity, Direction direction,
                      String accountNumber, Operation operation) {
        super();
        this.key = new PrimaryKey();
        this.key.setTradeId(tradeId);
        this.key.setTradeVersion(tradeVersion);
        this.accountNumber = accountNumber;
        this.securityIdentifier = securityIdentifier;
        this.quantity = quantity;
        this.direction = direction;
        this.operation = operation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TradeEvent other = (TradeEvent) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

    public PrimaryKey getKey() {
        return key;
    }

    public void setKey(PrimaryKey key) {
        this.key = key;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    public void setSecurityIdentifier(String securityIdentifier) {
        this.securityIdentifier = securityIdentifier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return String.format(Constant.STRING_FORMAT_FOR_TRADE_EVENT,
                key.getTradeId(), key.getTradeVersion(), getAccountNumber(), getSecurityIdentifier(),
                getQuantity(), getDirection(), getOperation());
    }

    public class PrimaryKey {
        private Integer tradeId;
        private int tradeVersion;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
            result = prime * result + tradeVersion;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PrimaryKey other = (PrimaryKey) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (tradeId == null) {
                if (other.tradeId != null)
                    return false;
            } else if (!tradeId.equals(other.tradeId))
                return false;
            if (tradeVersion != other.tradeVersion)
                return false;
            return true;
        }

        private TradeEvent getOuterType() {
            return TradeEvent.this;
        }

        public Integer getTradeId() {
            return tradeId;
        }

        public void setTradeId(Integer tradeId) {
            this.tradeId = tradeId;
        }

        public int getTradeVersion() {
            return tradeVersion;
        }

        public void setTradeVersion(int tradeVersion) {
            this.tradeVersion = tradeVersion;
        }

        @Override
        public String toString() {
            return "PrimaryKey [tradeId=" + tradeId + ", tradeVersion=" + tradeVersion + "]";
        }
    }
}
