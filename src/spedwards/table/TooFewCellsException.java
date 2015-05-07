package spedwards.table;

public class TooFewCellsException extends TableException {

	public TooFewCellsException(String message) {
		super(message);
	}

	public TooFewCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
