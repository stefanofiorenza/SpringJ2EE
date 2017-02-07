package corso.jms.basic.consumer.transazione;


import javax.jms.Session;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.ActiveMqUtils;
import corso.jms.basic.config.Configs;
import corso.jms.basic.config.JBossUtils;

public class JmsQueueConsumerTransaction {

	public static void main(String[] args) {
		try{
			JmsConsumer queueConsumer = JBossUtils.createQueueConsumerJboss71(Configs.SESSION_TRANSACTIONAL,Session.AUTO_ACKNOWLEDGE);			
			//JmsGenericConsumer queueConsumer =ActiveMqUtils.createQueueConsumerActiveMq(Configs.SESSION_TRANSACTIONAL,Session.AUTO_ACKNOWLEDGE);
			queueConsumer.startConnection();
			
			queueConsumer.pollingForOneTextMessage();
			//queueConsumer.pollingForAllMessages(Configs.MESSAGE_EXIT);
			
			queueConsumer.closeCommunication();
			
		} catch (Exception e){
			e.printStackTrace();
		}			
    }

}
