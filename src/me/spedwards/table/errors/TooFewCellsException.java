package me.spedwards.table.errors;

public class TooFewCellsException extends CellsException {

	public TooFewCellsException(String message) {
		super(message);
	}
	
	public TooFewCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}

}