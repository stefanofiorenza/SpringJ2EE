package apps.stockexchange.borsa;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.service.OrderService;
import apps.stockexchange.service.OrderUtils;

public class OrderServiceImpl implements OrderService{

	@Override
	public OrderOutcome processOrder(Order order) {
		OrderOutcome outcome=OrderUtils.evaluateOrder(order);
		
		//1) Send outcome to 
		sendOutcomeToReplyQueue(outcome);
		
		return outcome;
		
	}
	
	
	private boolean sendOutcomeToReplyQueue(OrderOutcome outcome){
		return false;
	}

}
