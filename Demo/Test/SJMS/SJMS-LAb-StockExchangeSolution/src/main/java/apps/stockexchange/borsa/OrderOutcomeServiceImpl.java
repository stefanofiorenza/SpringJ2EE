package apps.stockexchange.borsa;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import apps.stockexchange.beans.OrderOutcome;

public class OrderOutcomeServiceImpl implements OrderOutcomeService{

	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private DestinationRegistry destinationRegistry;
	
	@Override
	public boolean sendToReplyQueue(OrderOutcome outcome) {

		Destination destination=destinationRegistry.getDestination(outcome.getStockAgentId());
		
		jmsTemplate.send(destination, new MessageCreator() {
			
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
