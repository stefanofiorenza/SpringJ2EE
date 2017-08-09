package apps.stockexchange.stockagent.exceptions;

public class OrderOutcomeFormatException extends RuntimeException{

	public OrderOutcomeFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);	
	}

	public OrderOutcomeFormatException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public OrderOutcomeFormatException(String message) {
		super(message);		
	}

	public OrderOutcomeFormatException(Throwable cause) {
		super(cause);		
	}

	
}
