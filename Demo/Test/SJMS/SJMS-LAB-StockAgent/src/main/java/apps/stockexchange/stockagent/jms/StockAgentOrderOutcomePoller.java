package apps.stockexchange.stockagent.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.stockagent.exceptions.OrderOutcomeFormatException;
import apps.stockexchange.stockagent.exceptions.StockAgentApplicationException;
import apps.stockexchange.stockagent.services.OrderOutcomeProcessor;
import apps.stockexchange.stockagent.services.StockAgentUtils;

@Slf4j
@Service
public class StockAgentOrderOutcomePoller extends JmsGatewaySupport{

	@Autowired
	private OrderOutcomeProcessor orderOutcomeProcessor;
		
	@Getter
	@Setter
	private Destination destination;
	
	public void pollingForAllMessages() throws JMSException{
		
		
		log.info("Start polling for messages..");
		int consumed=0;
		OrderOutcome outcome=null;
		
		do{
			outcome=pollingForNextMessage();
			orderOutcomeProcessor.processOrderOutcome(outcome);		
			consumed++;
		}
		while(outcome!=null && !outcome.isExitMessage());	
					
		log.info("StockAgentPoller: Polling ended for Order Reply messages. Consumed: {} Messages ",consumed);
	}
	
	
	public OrderOutcome pollingForNextMessage(){

		OrderOutcome orderOutcome=null;
		try{
			Message message = getJmsTemplate().receive(getDestination());
			orderOutcome= StockAgentUtils.messageToBean(message);
			
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
			
		return orderOutcome;
		
	}
	
	
}
