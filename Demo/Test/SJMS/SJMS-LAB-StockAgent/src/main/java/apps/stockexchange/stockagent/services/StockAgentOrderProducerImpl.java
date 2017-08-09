package apps.stockexchange.stockagent.services;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;

@Service
@Slf4j
public class StockAgentOrderProducerImpl implements StockAgentOrderProducer{
	
	@Autowired
	@Qualifier("jmsTemplateStockProducer")
	private JmsTemplate jmsOrderSender;
	
	@Override
	public void sendOrder(Order order) {
		
		log.info("[StockAgent]: Sending order: {}",order.getOrderId());
		
		jmsOrderSender.send(new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
	
					ObjectMessage jmsMessage=session.createObjectMessage();
					jmsMessage.setObject(order);
					return jmsMessage;
				}
			});
		
		log.info("[StockAgent]: Order: {} Sent",order.getOrderId());
		
	}
	
	
	

}
