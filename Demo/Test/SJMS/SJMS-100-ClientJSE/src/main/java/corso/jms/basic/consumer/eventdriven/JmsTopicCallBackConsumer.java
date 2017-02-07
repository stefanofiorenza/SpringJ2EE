package corso.jms.basic.consumer.eventdriven;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.extern.slf4j.Slf4j;
import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.ActiveMqUtils;
import corso.jms.basic.config.JBossUtils;
import corso.jms.basic.consumer.listener.TextMessageListener;


@Slf4j
public class JmsTopicCallBackConsumer {


//	public static String DESTINATION="topic/testTopic";
//	private static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
//	private static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	
	
	public static void main(String[] args) {
				
		try {

			//JmsGenericConsumer queueConsumer = JBossUtils.createTopicConsumerJboss71();	
			JmsConsumer topicSubscriber =ActiveMqUtils.createTopicConsumerActiveMq();
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
