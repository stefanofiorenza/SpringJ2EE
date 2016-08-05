package corso.spring.jms.demo.consumers;


import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;



public class SpringQueueConsumer01Polling {

	public static final String EXIT_MESSAGE="closeConsumer";
		
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
	
	
	public boolean receiveAllTextMessage(){
		try{
			
			System.out.println("In attesa di ricevere messaggio..");
			String exit="";
			
			while(!exit.equals(EXIT_MESSAGE)){
				Message message = jmsTemplate.receive(destination);
				System.out.println("Trovato un messaggio in "+destination.toString());
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					exit=textMessage.getText();
					System.out.println("Ricevuto: "+exit);
				}else{
					throw new JMSException("Not a TextMessage!!");
				}
			}			
	    }	    
	    catch (JMSException e) {
	      System.out.println("JMS Exception "+e.getMessage());
	      return false;
	    }	    
	    return true;
	}
	
	
	public boolean receiveSingleTextMessage(){
		try{
			
			System.out.println("In attesa di ricevere messaggio..");
			Message message = jmsTemplate.receive(destination);
			System.out.println("A message exists...");
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Ricevuto: "+textMessage.getText());
			}else{
				throw new JMSException("Not a TextMessage!!");
			}				
	    }	    
	    catch (JMSException e) {
	      System.out.println("JMS Exception "+e.getMessage());
	      return false;
	    }	    
	    return true;
	}
	
	

}
