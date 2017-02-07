package corso.spring.jms.demo.consumers;


import javax.jms.Destination;
import javax.jms.JMSException;

import lombok.Getter;
import lombok.Setter;

import org.springframework.jms.core.JmsTemplate;



public class SpringQueueConsumer01Polling {

	
	public static final String EXIT_MESSAGE="closeConsumer";
	
	@Getter
	@Setter
	private JmsTemplate jmsTemplate;
	
	@Getter
	@Setter
	private Destination destination;
		
	
	public void receiveAllTextMessage(){	
		try{
			JmsConsumerHelper.pollingForAllMessages(jmsTemplate, EXIT_MESSAGE);	
		}catch (JMSException e) {
			 e.printStackTrace();
		 } 
	 }
	
	
	public void receiveSingleTextMessage(){
		try{
			JmsConsumerHelper.pollingForOneTextMessage(jmsTemplate);	
		 } catch (JMSException e) {
			 e.printStackTrace();
		 }
	}
	
	

}
