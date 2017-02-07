package corso.jms.basic.config;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.basic.common.JmsConsumer;

public class JBossUtils {

	public static final String TOPIC_JBOSS71 = "jms/topic/test";		
	public static final String QUEUE_JBOSS71 = "jms/queue/test";	
	public static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
	public static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	public static final String CONNECTION_FACTORY_JBOSS71="jms/RemoteConnectionFactory";
	
	
	public static JmsConsumer createQueueConsumerJboss71() throws NamingException {	
		return createQueueConsumerJboss71(Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE);
	}
	
	public static JmsConsumer createTopicConsumerJboss71() throws NamingException {	
		return createTopicConsumerJboss71(Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE);
	}
	
	public static JmsConsumer createQueueConsumerJboss71(boolean sessionTransactional, int autoAcknowledge) throws NamingException{
		
		//Riferimento al servizio JNDI
		Context context = getInitialContext(); 				
		
		//Ottengo un factory per la costruzione di una queue connection
		QueueConnectionFactory qcf = lookupQueueConnectionFactory(context);			
		
		//Ottengo un riferimento alla destination
		Queue queueReference =lookupQueue(context);
		
		return new JmsConsumer(qcf, queueReference, 
								Configs.JBOSS_71_QUEUE_USER, Configs.JBOSS_71_QUEUE_PW, 
								sessionTransactional, autoAcknowledge);	
	}
	
	
	
	
	public static JmsConsumer createTopicConsumerJboss71(boolean sessionTransactional, int autoAcknowledge) throws NamingException {
		
		Context context = getInitialContext();
		QueueConnectionFactory qcf = lookupQueueConnectionFactory(context);			
		Topic topicReference =lookupTopic(context);
		
		return new JmsConsumer(qcf, topicReference, 
				Configs.JBOSS_71_QUEUE_USER, Configs.JBOSS_71_QUEUE_PW, 
				Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE);
		
	}
	
	
	
	private static QueueConnectionFactory lookupQueueConnectionFactory(Context context) throws NamingException{
		return (QueueConnectionFactory) context.lookup(JBossUtils.CONNECTION_FACTORY_JBOSS71);			
	}
	
	private static Queue lookupQueue(Context context) throws NamingException{
		return (Queue)context.lookup(JBossUtils.QUEUE_JBOSS71);					
	}
	
	private static Topic lookupTopic(Context context) throws NamingException{
		return (Topic)context.lookup(JBossUtils.TOPIC_JBOSS71);					
	}
	
	private static QueueConnection createQueueConnection(QueueConnectionFactory qcf) throws JMSException{
		return  qcf.createQueueConnection("testuser","testuserpw");
	}
	
	private static Context getInitialContext(){
//		Properties properties = new Properties();
//		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
//		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		InitialContext jndiContext = null;
		try{
			// Get an initial context
		   jndiContext = new InitialContext();       		      
		}catch(NamingException e){
			System.out.println("Context Error: "+e.getMessage());
		}
		return jndiContext;
	}



	
	
}
