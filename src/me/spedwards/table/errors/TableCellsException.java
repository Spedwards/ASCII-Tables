package me.spedwards.table.errors;

public class TableCellsException extends TableException {

	public TableCellsException(String message) {
		super(message);
	}
	
	public TableCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}