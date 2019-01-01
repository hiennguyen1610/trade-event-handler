package com.jpmorgan.tradehandler.model;

import com.jpmorgan.tradehandler.common.Constant;

public class AggregatePosition {
	private PrimaryKey key = new PrimaryKey();
	private int quantity = 0;
	private String trades = "";

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
		AggregatePosition other = (AggregatePosition) obj;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTrades() {
		return trades;
	}

	public void setTrades(String trades) {
		this.trades = trades;
	}

	public void appendTradeIdToTrades(String tradeId) {
		if (trades.isEmpty())
			trades = tradeId;
		else {
			if (!trades.contains(tradeId))
				trades += ", " + tradeId;
		}
	}

	@Override
	public String toString() {
		return String.format(Constant.STRING_FORMAT_FOR_AGGREGATE_POSITION, "", "", key.getAccountNumber(),
				key.getSecurityIdentifier(), quantity, trades);
	}

	public class PrimaryKey {
		private String accountNumber = "";
		private String securityIdentifier = "";

		public PrimaryKey() {
		}

		public PrimaryKey(String accountNumber, String securityIdentifier) {
			super();
			this.accountNumber = accountNumber;
			this.securityIdentifier = securityIdentifier;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
			result = prime * result + ((securityIdentifier == null) ? 0 : securityIdentifier.hashCode());
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
			if (accountNumber == null) {
				if (other.accountNumber != null)
					return false;
			} else if (!accountNumber.equals(other.accountNumber))
				return false;
			if (securityIdentifier == null) {
				if (other.securityIdentifier != null)
					return false;
			} else if (!securityIdentifier.equals(other.securityIdentifier))
				return false;
			return true;
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

		@Override
		public String toString() {
			return "PrimaryKey [accountNumber=" + accountNumber + ", securityIdentifier=" + securityIdentifier + "]";
		}

	}
}
