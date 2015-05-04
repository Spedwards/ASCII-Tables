package me.spedwards.table.errors;

public class CellsException extends RuntimeException {

	public CellsException(String message) {
		super(message);
	}
	
	public CellsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}