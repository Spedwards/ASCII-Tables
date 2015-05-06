package me.spedwards.table.errors;

public class TooManyCellsException extends TableCellsException {

	public TooManyCellsException(String message) {
		super(message);
	}
	
	public TooManyCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}

}