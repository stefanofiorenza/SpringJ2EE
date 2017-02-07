package corso.jms.basic.producer;

import corso.jms.basic.common.JmsProducer;
import corso.jms.basic.config.Configs;
import corso.jms.basic.config.JndiUtils;

public class JmsTopicProducer {


	public static void main(String[] args) {
		
		JmsProducer topicProducer=JndiUtils.loadJmsProducer();
		topicProducer.startConnection();
				
		topicProducer.sendTextMessage("OneMessage");
		topicProducer.sendTextMessage(Configs.MESSAGE_EXIT);// to end consumers
		
		topicProducer.sendManyTextMessage(40,4);//ogni 4 messaggi il consumer andra in eccezione
		
		topicProducer.closeCommunication();
	}

}
