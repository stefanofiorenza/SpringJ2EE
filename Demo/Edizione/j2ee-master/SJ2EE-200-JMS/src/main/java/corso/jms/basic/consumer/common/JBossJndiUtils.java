package corso.jms.basic.consumer.common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JBossJndiUtils {

	public static final String TOPIC_JBOSS71 = "jms/topic/test";		
	public static final String QUEUE_JBOSS71 = "jms/queue/test";	
	public static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
	public static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	public static final String CONNECTION_FACTORY_JBOSS71="jms/RemoteConnectionFactory";
	
	
	public static Context getInitialContext(){
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
