package corso.spring.messaging.demo.jms.topic;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsTopicProducer {

	/**
	 * @param args
	 */
	
	private Topic destination;
	private TopicConnectionFactory topicConnectionFactory;
	private TopicConnection topicConnection;
	
	
	public static String DESTINATION="topic/testTopic";
	
	public JmsTopicProducer() throws NamingException, JMSException{
		
		Context JbossContext = getInitialContext();
		topicConnectionFactory = (TopicConnectionFactory) JbossContext.lookup("ConnectionFactory");			
		destination =(Topic)JbossContext.lookup(JmsTopicProducer.DESTINATION);
		topicConnection =topicConnectionFactory.createTopicConnection();
	}

	
	public boolean sendObjectMessage(Serializable obj){
		
		TopicSession sessione= null;
		TopicPublisher publisher =null;
		
		try{
			
			 sessione= topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			 publisher = sessione.createPublisher(destination);
			
			// apro connessione
			topicConnection.start();
			
			//Creo un messaggio 
			ObjectMessage message = sessione.createObjectMessage();
			message.setObject(obj);
			
			//Spedisco
			publisher.publish(message);
			
			
			System.out.println("Messaggio spedito");
			
		}catch (JMSException e) {
			System.out.println("JMS Exception "+e.getMessage());
			return false;
		}
		finally{
			try {
				sessione.close();
				topicConnection.close();
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
		
	}
	public boolean sendTextMessage(String text){
		try{
			
			
			TopicSession sessione= topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher publisher = sessione.createPublisher(destination);
			
			// apro connessione
			topicConnection.start();
						
			//Creo un messaggio di testo
			TextMessage message = sessione.createTextMessage();
			message.setText(text);
			
			//Spedisco
			publisher.send(message);
			
			
			System.out.println("Messaggio spedito");
			
			
			
			
		}catch (JMSException e) {
			System.out.println("JMS Exception "+e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public void closeCommunication(){
		if (topicConnection != null) {
			try {
				
				topicConnection.close();
			}
			catch (JMSException e) {
				
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSendMessages (30);
	
		
	}
	
	private static Context getInitialContext(){
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		InitialContext jndiContext = null;
		try{
			// Get an initial context
		   jndiContext = new InitialContext(properties);       		      
		}catch(NamingException e){
			System.out.println("Context Error: "+e.getMessage());
		}
		return jndiContext;
	}
	
	
	public static void testSendMessages (int quanti){
		
		try {
			JmsTopicProducer producer= new JmsTopicProducer();
			
			Random rnd = new Random();
			
			
			for (int i=0;i<quanti;i++){
				
				int secondi =rnd.nextInt(3000);
				
				try {
					Thread.sleep(secondi);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				producer.sendTextMessage("Messaggio "+i);
				
			}
			
			
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
public static void testSendOneMessage (){
		
		try {
			JmsTopicProducer producer= new JmsTopicProducer();
			
			
			
			producer.sendTextMessage("Messaggio:ciao");
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
