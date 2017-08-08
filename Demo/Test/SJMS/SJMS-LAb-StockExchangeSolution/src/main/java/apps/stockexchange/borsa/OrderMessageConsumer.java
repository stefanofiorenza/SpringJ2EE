package apps.stockexchange.borsa;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.borsa.exceptions.OrderFormatException;
import apps.stockexchange.borsa.exceptions.OrderServiceException;
import apps.stockexchange.service.OrderService;
import apps.stockexchange.service.OrderUtils;

@Slf4j
public class OrderMessageConsumer {

	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderOutcomeService orderOutcomeService;
	
	
	@JmsListener(destination = "see.jms.test.queue")
	public void processMessage(Message message){
		
		ObjectMessage objMessage=(ObjectMessage)message;
		Order order=null;
		OrderOutcome outcome=null;
		
		try {
			
			
			if(!message.isBodyAssignableTo(Order.class)){				
				throw new OrderFormatException("Message is not of <Order>.class type");				
			}
			
			//order = (Order)objMessage.getObject();
			order = objMessage.getBody(Order.class);
			
			outcome=orderService.processOrder(order);
			orderOutcomeService.sendToReplyQueue(outcome);
		
			
		} catch (JMSException e) {
			throw new OrderFormatException(e);
			
		} catch (OrderFormatException e){
			
			//1) save message Dump with messageId
			
			//2) Log Exception
			
		} catch (OrderServiceException e){
			//Should never happen !!
			log.error(e.getMessage(),e);
		}
		
	}
	
	
	
}
