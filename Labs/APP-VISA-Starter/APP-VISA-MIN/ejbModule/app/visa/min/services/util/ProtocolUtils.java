package app.visa.min.services.util;

public class ProtocolUtils {

	private static final String PROTOCOL_MIN="MIN";
	private static int PROTOCOL_NUMBER=0;
	
	
	public static String nextProtocol(){
		PROTOCOL_NUMBER++;
		return PROTOCOL_MIN+PROTOCOL_NUMBER;
	}
}
