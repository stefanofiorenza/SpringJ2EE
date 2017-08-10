package apps.stockexchange.stockagent.main;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import apps.stockexchange.beans.Order;
import apps.stockexchange.generator.StockOrderGenerator;
import apps.stockexchange.stockagent.services.StockAgentOrderProducer;

@Slf4j
public class StockAgentMain {

	
	private static ExecutorService executor = Executors.newFixedThreadPool(3);
	
	private static int ORDERS=1000;
	
	public static void main(String[] args) {

		ApplicationContext context =new ClassPathXmlApplicationContext("se-stockagent-main.xml");
		sendOrdersThread(context);
		
	}
	
	
	private static void receiveOrderRepliesThread(ApplicationContext context){
		
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				try{
					receiveActions(context);					
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}		
			}				
		});		
	}
	
	private static void sendOrdersThread(ApplicationContext context){
		
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				try{
					sendingActions(context);					
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}		
			}				
		});				
	}

	private static void receiveActions(ApplicationContext context){	
		
	}
	
	
	
	private static void sendingActions(ApplicationContext context){		
	
		StockAgentOrderProducer stockProducer=context.getBean(StockAgentOrderProducer.class);
		Destination orderDestination=context.getBean("jmsQueueOrder",Destination.class);
		
		List<Order> orders= StockOrderGenerator.generateOrders(ORDERS);
		
		orders.forEach(order -> {
			log.info("Sending Order: {} to {} ",order.toString(),orderDestination.toString());
			stockProducer.sendOrder(order);
		});		
	}
	

}
