package app.visa.min.services.exceptions;

public class CittadinoException extends RuntimeException {

	public CittadinoException(Exception ecc){
		super(ecc);
	}
	
	public CittadinoException(String causa){
		super(causa);
	}
}
