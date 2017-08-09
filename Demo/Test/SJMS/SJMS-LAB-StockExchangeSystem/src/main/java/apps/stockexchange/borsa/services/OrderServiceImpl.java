package apps.stockexchange.borsa.services;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.service.OrderService;
import apps.stockexchange.service.OrderUtils;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

	@Override
	public OrderOutcome processOrder(Order order) {
		log.info("Execute updates");
		return OrderUtils.evaluateOrder(order);		
	}
	
	
	

}
