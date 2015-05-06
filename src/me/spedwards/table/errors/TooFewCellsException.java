package me.spedwards.table.errors;

public class TooFewCellsException extends TableCellsException {

	public TooFewCellsException(String message) {
		super(message);
	}
	
	public TooFewCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}

}