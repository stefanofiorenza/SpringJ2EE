package apps.stockexchange.borsa.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.borsa.exceptions.OrderFormatException;
import apps.stockexchange.borsa.exceptions.OrderServiceException;
import apps.stockexchange.borsa.services.MessageRouter;
import apps.stockexchange.service.OrderService;
import apps.stockexchange.service.OrderUtils;

@Slf4j
public class OrderMessageConsumer implements MessageListener{

	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MessageRouter messageRouter;
	
	@Autowired
	@Qualifier("jmsTemplateBorsaReplySender")
	private JmsTemplate jmsReplySender;
	
	
	@Override
	public void onMessage(Message message){
		
		ObjectMessage objMessage=(ObjectMessage)message;
		Order order=null;
		OrderOutcome outcome=null;
		
		try {			
			
//			if(!message.isBodyAssignableTo(Order.class)){				
//				throw new OrderFormatException("Message is not of <Order>.class type");				
//			}
			
			order = (Order)objMessage.getObject();
			//order = objMessage.getBody(Order.class);			
			outcome=orderService.processOrder(order);
			
			sendToReplyQueue(outcome);
		
			
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
	
	
	private boolean sendToReplyQueue(OrderOutcome outcome) {
		
		
		String destination =messageRouter.routeOrder(outcome);
		
		jmsReplySender.send(destination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {

				ObjectMessage jmsMessage=session.createObjectMessage();
				jmsMessage.setObject(outcome);
				return jmsMessage;
			}
		});
		return false;
	}
}
