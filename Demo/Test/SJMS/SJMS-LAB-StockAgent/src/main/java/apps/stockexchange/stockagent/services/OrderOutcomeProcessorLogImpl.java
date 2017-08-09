package apps.stockexchange.stockagent.services;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import apps.stockexchange.beans.OrderOutcome;

@Service
@Slf4j
public class OrderOutcomeProcessorLogImpl implements OrderOutcomeProcessor{

	@Override
	public void processOrderOutcome(OrderOutcome outcome) {
		log.info("Order#{} received Reply {} ",outcome.getOrderId(), outcome.toString());		
	}

	

}
