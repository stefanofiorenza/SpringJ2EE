package apps.stockexchange.borsa;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.jms.Destination;

import lombok.Getter;

public class DestinationRegistry {

	
	@Getter
	private Map<String, Destination> stockAgencyDestinations = new ConcurrentHashMap<String, Destination>();

	public Destination getDestination(String customerId){
		return stockAgencyDestinations.get(customerId);
	}
}
