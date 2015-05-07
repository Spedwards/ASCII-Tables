package spedwards.table;

public class TooManyCellsException extends TableException {

	public TooManyCellsException(String message) {
		super(message);
	}

	public TooManyCellsException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
