package apps.stockexchange.stockagent.exceptions;

public class StockAgentApplicationException extends RuntimeException{

	public StockAgentApplicationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);	
	}

	public StockAgentApplicationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public StockAgentApplicationException(String message) {
		super(message);		
	}

	public StockAgentApplicationException(Throwable cause) {
		super(cause);		
	}

	
}
