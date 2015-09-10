package corso.spring.messaging.demo.jms.queue;


import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;



public class SpringQueueConsumer {

	private JmsTemplate jmsTemplate;
	private Destination destination;
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
	public boolean receiveTextMessage(){
		try{
			
			System.out.println("In attesa di ricevere messaggio..");
			Message message = jmsTemplate.receive(destination);
		
			if (message != null) {
				System.out.println("trovato un messaggio!");
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println(textMessage.getText());
				}
			}
	    }	    
	    catch (JMSException e) {
	      System.out.println("JMS Exception "+e.getMessage());
	      return false;
	    }	    
	    return true;
	}
	
	

}
