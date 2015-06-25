package corso.jms.basic.consumer.eventdriven;

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

import corso.jms.basic.consumer.listener.TextMessageListener;


public class JmsTopicCallBackConsumer {


	public static String DESTINATION="topic/testTopic";
	private static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
	private static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	

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
	
	
	
	
	
	 
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Topic destination=null;
		 TopicConnectionFactory topicConnectionFactory=null;
		 TopicConnection topicConnection=null;
		 TextMessageListener listener=null; 
		
		try {

			 
			
			Context JbossContext = getInitialContext();
			topicConnectionFactory = (TopicConnectionFactory) JbossContext.lookup(CONNECTION_FACTORY_JBOSS51);			
			destination =(Topic)JbossContext.lookup(JmsTopicCallBackConsumer.DESTINATION);
			topicConnection =topicConnectionFactory.createTopicConnection();
			listener= new TextMessageListener("Topic Consumer");	
			
			
			TopicSession sessione = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE );
			TopicSubscriber subscriber= sessione.createSubscriber(destination);
			subscriber.setMessageListener(listener);
			
			topicConnection.start();
			System.out.println("In attesa di ricevere messaggio..");
			
			
			// Rimane in attesa per 30 secondi...
			 try
	            {
	              Thread.sleep(30000);
	            } catch (Exception e) {
	            
	            	e.printStackTrace();
	            }
	           
	            System.out.println("Scollegato");
				  
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				topicConnection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}



	

}
