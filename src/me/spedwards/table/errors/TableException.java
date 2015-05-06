package me.spedwards.table.errors;

public class TableException extends RuntimeException {

	public TableException(String message) {
		super(message);
	}
	
	public TableException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}