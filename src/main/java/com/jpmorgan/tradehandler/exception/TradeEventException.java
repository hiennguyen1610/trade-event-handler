package com.jpmorgan.tradehandler.exception;

public class TradeEventException extends RuntimeException {
	private static final long serialVersionUID = 6749683528986475583L;

	public TradeEventException() {
		super();
	}

	public TradeEventException(String message) {
		super(message);
	}

	public TradeEventException(String message, Throwable cause) {
		super(message, cause);
	}

	public TradeEventException(Throwable cause) {
		super(cause);
	}
}
