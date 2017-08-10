package apps.stockexchange.service;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceLogImpl implements OrderService {

	public OrderOutcome processOrder(Order order) {
		log.info("Received order with Data: \n {}",order.toString());		
		return OrderUtils.evaluateOrder(order);		
	}

}
