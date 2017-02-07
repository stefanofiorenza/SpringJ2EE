package corso.jms.basic.producer;

import corso.jms.basic.common.JmsProducer;
import corso.jms.basic.config.ActiveMqUtils;
import corso.jms.basic.config.Configs;

public class JmsTopicProducer {

	private static JmsProducer topicProducer;
	
	public static void main(String[] args) {
		
		topicProducer =ActiveMqUtils.createQueueProducerActiveMq();
		topicProducer.startConnection();
				
		topicProducer.sendTextMessage("OneMessage");
		topicProducer.sendTextMessage(Configs.MESSAGE_EXIT);// to end consumers
		
		topicProducer.sendManyTextMessage(40,4);//ogni 4 messaggi il consumer andra in eccezione
	}

}
