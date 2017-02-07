package corso.spring.jms.demo.producers;

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
	public boolean sendObjectMessage(final Serializable obj) {
					
			//Creo un messaggio 
			this.jmsTemplate.send(this.destination, new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						ObjectMessage message = session.createObjectMessage();
						message.setObject(obj);
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
	

	
}
