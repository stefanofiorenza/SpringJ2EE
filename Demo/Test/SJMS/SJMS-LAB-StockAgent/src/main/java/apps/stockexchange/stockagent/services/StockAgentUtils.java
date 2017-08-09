package apps.stockexchange.stockagent.services;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.stockagent.exceptions.OrderOutcomeFormatException;
import apps.stockexchange.stockagent.exceptions.StockAgentApplicationException;

public class StockAgentUtils {

	
	public static OrderOutcome messageToBean(Message message){
			try {			
			ObjectMessage objMessage=(ObjectMessage)message;	
			OrderOutcome outcome= (OrderOutcome)objMessage.getObject();					
			return outcome;			
			
		} catch (JMSException e) {
			throw new OrderOutcomeFormatException(e);
			
		} catch (Exception e){
			throw new StockAgentApplicationException(e);
		}
	}
}
