package corso.jms.basic.consumer.polling;


import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.ActiveMqUtils;

public class JmsQueueConsumer {

	public static void main(String[] args) {
		try{
			//JmsGenericConsumer queueConsumer = JBossUtils.createQueueConsumerJboss71();			
			JmsConsumer queueConsumer =ActiveMqUtils.createQueueConsumerActiveMq();
			queueConsumer.startConnection();
			
			queueConsumer.pollingForOneTextMessage();
			//queueConsumer.pollingForAllMessages(Configs.MESSAGE_EXIT);
			
			queueConsumer.closeCommunication();
			
		} catch (Exception e){
			e.printStackTrace();
		}			
    }

}
