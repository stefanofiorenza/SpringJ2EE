package corso.jms.basic.consumer.eventdriven;

import lombok.extern.slf4j.Slf4j;
import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.JndiUtils;
import corso.jms.basic.consumer.listener.TextMessageListener;


@Slf4j
public class JmsTopicCallBackConsumer {


	
	
	public static void main(String[] args) {
				
		try {
			
			JmsConsumer topicSubscriber =JndiUtils.loadJmsConsumer();
			topicSubscriber.setTextMessageListener(new TextMessageListener("TopicConsumerListener"));
			topicSubscriber.startConnection();
					
			// Rimane in attesa per 30 secondi...
		    Thread.sleep(30000);
	            
            log.info("Scollegato");
            topicSubscriber.closeCommunication();
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();			
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	

}
