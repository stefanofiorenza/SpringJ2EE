package corso.jms.basic.consumer.eventdriven;


import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.qpid.jms.JmsConnectionFactory;

import lombok.extern.slf4j.Slf4j;
import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.ActiveMqUtils;
import corso.jms.basic.config.Configs;
import corso.jms.basic.config.DestinationType;
import corso.jms.basic.config.JBossUtils;
import corso.jms.basic.consumer.listener.TextMessageListener;

@Slf4j
public class JmsQueueCallBackConsumer {

	
	
	public static void main(String[] args) {
				
		// rimane collegato per 10 secondi
		try {
			//JmsGenericConsumer queueConsumer = JBossUtils.createQueueConsumerJboss71();			
			JmsConsumer queueConsumer =ActiveMqUtils.createQueueConsumerActiveMq();
			
			queueConsumer.setTextMessageListener(new TextMessageListener("QueueConsumerListener"));
			queueConsumer.startConnection();
			
			Thread.sleep(10000);	
			log.info("Scollega Consumer...");
			
			// poi chiude la connessione e termina il thread
			queueConsumer.closeCommunication();
			log.info("Consumer Scollegato ");
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}		
	  
    }
	

	
	


}
