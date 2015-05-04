package me.spedwards.table.errors;

public class TooManyCellsException extends CellsException {

	public TooManyCellsException(String message) {
		super(message);
	}
	
	public TooManyCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}

}