package corso.jms.basic.consumer.polling;


import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.JndiUtils;

public class JmsQueueConsumer {

	public static void main(String[] args) {
		try{
			JmsConsumer queueConsumer =JndiUtils.loadJmsConsumer();
			queueConsumer.startConnection();
			
			queueConsumer.pollingForOneTextMessage();
			//queueConsumer.pollingForAllMessages(Configs.MESSAGE_EXIT);
			
			queueConsumer.closeCommunication();
			
		} catch (Exception e){
			e.printStackTrace();
		}			
    }

}
