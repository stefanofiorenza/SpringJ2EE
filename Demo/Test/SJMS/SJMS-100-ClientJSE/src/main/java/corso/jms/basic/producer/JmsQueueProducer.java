package corso.jms.basic.producer;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;









import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.common.JmsProducer;
import corso.jms.basic.config.ActiveMqUtils;
import corso.jms.basic.config.Configs;
import corso.jms.basic.config.JBossUtils;
import corso.jms.basic.consumer.polling.JmsQueueConsumer;

public class JmsQueueProducer {


	private static JmsProducer queueProducer;
	
	public static void main(String[] args) {
		 
		
		
		//JmsGenericConsumer queueConsumer = JBossUtils.createQueueConsumerJboss71();			
		queueProducer =ActiveMqUtils.createQueueProducerActiveMq();
		queueProducer.startConnection();
				
		queueProducer.sendTextMessage("OneMessage");
		queueProducer.sendTextMessage(Configs.MESSAGE_EXIT);// to end consumers
		
		//queueProducer.sendManyTextMessage(40,4);//ogni 4 messaggi il consumer andra in eccezione		
	}
	
	
}
