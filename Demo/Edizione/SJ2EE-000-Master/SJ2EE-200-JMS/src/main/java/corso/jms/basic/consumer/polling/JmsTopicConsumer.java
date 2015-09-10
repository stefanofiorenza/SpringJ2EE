package corso.jms.basic.consumer.polling;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.basic.consumer.common.JBossJndiUtils;


public class JmsTopicConsumer {

	private Topic destination;
	private TopicConnectionFactory topicConnectionFactory;
	private TopicConnection topicConnection;
	TopicSession sessione;
	
//	public static String DESTINATION="topic/testTopic";
//	private static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
//	private static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	

//	private static Context getInitialContext(){
//		Properties properties = new Properties();
//		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
//		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
//		InitialContext jndiContext = null;
//		try{
//			// Get an initial context
//		   jndiContext = new InitialContext(properties);       		      
//		}catch(NamingException e){
//			System.out.println("Context Error: "+e.getMessage());
//		}
//		return jndiContext;
//	}
	
	
	public JmsTopicConsumer() throws NamingException, JMSException{
		
		
		Context JbossContext = JBossJndiUtils.getInitialContext();
		topicConnectionFactory = (TopicConnectionFactory) JbossContext.lookup(JBossJndiUtils.CONNECTION_FACTORY_JBOSS71);			
		destination =(Topic)JbossContext.lookup(JBossJndiUtils.QUEUE_JBOSS71);
		topicConnection =topicConnectionFactory.createTopicConnection();
	
	}
	
	
	 
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JmsTopicConsumer consumer;
		try {
			consumer = new JmsTopicConsumer();
			consumer.receiveTextMessage();
			consumer.closeCommunication();
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	public boolean receiveTextMessage(){
		
		
		
		try{
			
			topicConnection.start();
			sessione = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE );
			TopicSubscriber subscriber= sessione.createSubscriber(destination);
			
			System.out.println("In attesa di ricevere messaggio..");
			
			Message message = subscriber.receive();
		
			//if (message != null) {
				System.out.println("Trovato un messaggio!!...");
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println(textMessage.getText());
				}
			//}
	    }	    
	    catch (JMSException e) {
	      System.out.println("JMS Exception "+e.getMessage());
	      return false;
	    }	    
	    return true;
	}

	public void closeCommunication(){
		if (topicConnection != null && sessione != null) {
			try {
				sessione.close();
				topicConnection.close();
			}
			catch (JMSException e) {
				
			}
		}
	}


	

}
