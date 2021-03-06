package corso.spring.messaging.demo.jms.queue;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;



import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringQueueProducer {

	
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
	public boolean sendObjectMessage(Serializable obj) {
					
			//Creo un messaggio 
			//TODO Spring 3.0 cambia il template
				this.jmsTemplate.send(this.destination, new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						Message message = session.createMessage();
						//message.setString("mailId", mail.getMailId());
						return message;
					}
				});
					
		return true;
		
	}
	public boolean sendTextMessage(final String text){
		//Creo un messaggio 
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(text);
				return message;
			}
		});
		return true;
	}
	

	/*
	public static void main(String[] args) {
		
		testSendOneMessage("Primo messaggio");
		//testSendMessages(40);
		
	}
	
	public static void testSendMessages (int quanti){
		
		SpringQueueProducer ms = new SpringQueueProducer();
		
		Random rnd = new Random();
				
		for (int i=0;i<quanti;i++){
			
			int secondi =rnd.nextInt(3000);
			
			try {
				Thread.sleep(secondi);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean sent = ms.sendTextMessage("messaggio "+i);
			
			if(sent)
				System.out.println("Text Message sent...");
			else
				System.out.println("Comunication problem...");	
		}
		
		
		
		
	}
	
	public static void testSendOneMessage (String messaggio){
		
		SpringQueueProducer ms = new SpringQueueProducer();
		boolean sent = ms.sendTextMessage(messaggio);
		
		
		if(sent)
			System.out.println("Text Message sent...");
		else
			System.out.println("Comunication problem...");		
		ms.closeCommunication();
	}
	
	*/
}
