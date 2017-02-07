package corso.spring.messaging.demo.jms.queue;


import javax.jms.Destination;
import javax.jms.JMSException;

import lombok.Getter;
import lombok.Setter;

import org.springframework.jms.core.JmsTemplate;



public class SpringQueueConsumer {

	@Getter
	@Setter
	private JmsTemplate jmsTemplate;
	
	@Getter
	@Setter
	private Destination destination;
	
	
	public void pollingOneTextMessage(){
		try{
			JmsConsumerHelper.pollingForOneTextMessage(getJmsTemplate());		
	    }	    
	    catch (JMSException e) {
	    	e.printStackTrace();
	    }	    
	}
	
	

}
