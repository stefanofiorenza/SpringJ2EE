package corso.spring.jms.demo.consumers;


import javax.jms.Destination;
import javax.jms.JMSException;

import lombok.Getter;
import lombok.Setter;

import org.springframework.jms.core.support.JmsGatewaySupport;



public class SpringQueueConsumer02PollingGateway extends JmsGatewaySupport{

	public static final String EXIT_MESSAGE="closeConsumer";
	
	@Getter
	@Setter
	private Destination destination;
		
	public void receiveAllTextMessage(){	
		try{
			JmsConsumerHelper.pollingForAllMessages(getJmsTemplate(), EXIT_MESSAGE);	
		}catch (JMSException e) {
			 e.printStackTrace();
		 } 
	 }
	
	
	public void receiveSingleTextMessage(){
		try{
			JmsConsumerHelper.pollingForOneTextMessage(getJmsTemplate());	
		 } catch (JMSException e) {
			 e.printStackTrace();
		 }
	}
	
	
	

}
