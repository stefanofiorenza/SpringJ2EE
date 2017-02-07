package corso.jms.basic.producer;

import corso.jms.basic.common.JmsProducer;
import corso.jms.basic.config.Configs;
import corso.jms.basic.config.JndiUtils;

public class JmsQueueProducer {

		
	public static void main(String[] args) {
					
		//JmsGenericConsumer queueConsumer = JBossUtils.createQueueConsumerJboss71();			
		JmsProducer queueProducer=JndiUtils.loadJmsProducer();
		queueProducer.startConnection();
				
		queueProducer.sendTextMessage("OneMessage");
		queueProducer.sendTextMessage(Configs.MESSAGE_EXIT);// to end consumers
		
		//queueProducer.sendManyTextMessage(40,4);//ogni 4 messaggi il consumer andra in eccezione
		
		queueProducer.closeCommunication();
	}
	
	
}
