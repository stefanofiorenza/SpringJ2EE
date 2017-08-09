package apps.stockexchange.stockagent.jms;

import javax.jms.Message;
import javax.jms.MessageListener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import apps.stockexchange.beans.OrderOutcome;
import apps.stockexchange.stockagent.services.OrderOutcomeProcessor;
import apps.stockexchange.stockagent.services.StockAgentUtils;

@Slf4j
public class StockAgentOrderOutcomeConsumer implements MessageListener{

	@Autowired
	private OrderOutcomeProcessor orderOutcomeProcessor;
	
	@Override
	public void onMessage(Message message) {
		try{
			OrderOutcome outcome=StockAgentUtils.messageToBean(message);
			orderOutcomeProcessor.processOrderOutcome(outcome);
			
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
	
		
	}

}
