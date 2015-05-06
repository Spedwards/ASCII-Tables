package me.spedwards.table.errors;

public class InvalidJSONException extends TableException {

	public InvalidJSONException(String message) {
		super(message);
	}
	
	public InvalidJSONException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}