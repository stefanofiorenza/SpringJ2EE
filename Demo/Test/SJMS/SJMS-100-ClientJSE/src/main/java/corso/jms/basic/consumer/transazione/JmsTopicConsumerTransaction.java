package corso.jms.basic.consumer.transazione;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.config.JndiUtils;


public class JmsTopicConsumerTransaction {

	
	
	public static void main(String[] args) {
		try{
		
			JmsConsumer topicConsumer =JndiUtils.loadJmsConsumer();			
			topicConsumer.startConnection();
			
			topicConsumer.pollingForOneTextMessage();
			//queueConsumer.pollingForAllMessages(Configs.MESSAGE_EXIT);
			
			topicConsumer.closeCommunication();
			
		} catch (Exception e){
			e.printStackTrace();
		}			
    }
	
	
//	private Topic destination;
//	private TopicConnectionFactory topicConnectionFactory;
//	private TopicConnection topicConnection;
//	TopicSession sessione;
	
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
	
	
//	public JmsTopicConsumer() throws NamingException, JMSException{
//		
//		
//		Context JbossContext = JBossUtils.getInitialContext();
//		topicConnectionFactory = (TopicConnectionFactory) JbossContext.lookup(JBossUtils.CONNECTION_FACTORY_JBOSS71);			
//		destination =(Topic)JbossContext.lookup(JBossUtils.QUEUE_JBOSS71);
//		topicConnection =topicConnectionFactory.createTopicConnection();
//	
//	}
//	
//	
//	 
//	
//	
//	
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		JmsTopicConsumer consumer;
//		try {
//			consumer = new JmsTopicConsumer();
//			consumer.receiveTextMessage();
//			consumer.closeCommunication();
//			
//			
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//	}
//
//
//	public boolean receiveTextMessage(){
//		
//		
//		
//		try{
//			
//			topicConnection.start();
//			sessione = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE );
//			TopicSubscriber subscriber= sessione.createSubscriber(destination);
//			
//			System.out.println("In attesa di ricevere messaggio..");
//			
//			Message message = subscriber.receive();
//		
//			//if (message != null) {
//				System.out.println("Trovato un messaggio!!...");
//				if (message instanceof TextMessage) {
//					TextMessage textMessage = (TextMessage) message;
//					System.out.println(textMessage.getText());
//				}
//			//}
//	    }	    
//	    catch (JMSException e) {
//	      System.out.println("JMS Exception "+e.getMessage());
//	      return false;
//	    }	    
//	    return true;
//	}
//
//	public void closeCommunication(){
//		if (topicConnection != null && sessione != null) {
//			try {
//				sessione.close();
//				topicConnection.close();
//			}
//			catch (JMSException e) {
//				
//			}
//		}
//	}


	

}
