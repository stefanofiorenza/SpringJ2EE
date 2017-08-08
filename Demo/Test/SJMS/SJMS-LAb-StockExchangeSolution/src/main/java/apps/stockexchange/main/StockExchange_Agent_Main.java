package apps.stockexchange.main;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import apps.stockexchange.beans.Order;
import apps.stockexchange.generator.StockOrderGenerator;

@Slf4j
public class StockExchange_Agent_Main {

	public static void main(String[] args) {


		List<Order> orders= StockOrderGenerator.generateOrders(10);
		
		orders.forEach(order -> log.info(order.toString()));
		
		//1) order 2 JmsMessage
		
		
		//2) print confirmation order accepted

	}
	
	

}
