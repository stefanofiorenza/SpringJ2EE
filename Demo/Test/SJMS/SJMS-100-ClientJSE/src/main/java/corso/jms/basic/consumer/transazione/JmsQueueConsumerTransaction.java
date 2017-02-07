package corso.jms.basic.consumer.transazione;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.JndiUtils;

public class JmsQueueConsumerTransaction {

	public static void main(String[] args) {
		try{
			JmsConsumer queueConsumer =JndiUtils.loadJmsConsumer();
			
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
