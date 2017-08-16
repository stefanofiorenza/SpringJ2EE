package apps.stockexchange.borsa.services;

import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;

@Slf4j
@Service
public class MessageRouterImpl implements MessageRouter{

	
	@Resource
	@Qualifier("stockAgentId2DestinationMap")
	private Map<String, String> stockAgentId2DestinationMap;
	
	
	@Override
	public String routeOrder(OrderOutcome orderOutcome) {
		return stockAgentId2DestinationMap.get(orderOutcome.getStockAgentId());
	}

}
