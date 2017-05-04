package corso.jms.demo.basic.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.demo.basic.common.JmsConsumer;
import corso.jms.demo.basic.common.JmsProducer;


public class JndiUtils {

	
	public static JmsConsumer loadJmsConsumer(String name){
								
			try {
				
				Properties config=bundleToProps(Configs.CONS_BUNDLE_NAME);
				
				//Riferimento al servizio JNDI
				Context context = getInitialContext(config); 	
				
				//Ottengo un factory per la costruzione di una queue connection
				QueueConnectionFactory qcf = (QueueConnectionFactory) context.lookup(config.getProperty(Configs.CONNECTION_FACTORY_CFG_KEY));
				
				String destinationType=config.getProperty(Configs.DEST_TYPE_MODE_KEY);
				
				Destination destination=null;
				
				switch(destinationType){
					case Configs.QUEUE_TYPE:
						destination=(Queue)context.lookup(config.getProperty((Configs.QUEUE_CFG_KEY)));
						break;
					case Configs.TOPIC_TYPE:
						destination=(Queue)context.lookup(config.getProperty((Configs.TOPIC_CFG_KEY)));
						break;
				}
						
				boolean transactional= Boolean.parseBoolean(config.getProperty(Configs.TRANSACTIONAL_KEY));
				int ackMode=Integer.parseInt(config.getProperty(Configs.ACK_MODE_KEY));
				
				return new JmsConsumer(name,qcf, destination, 
						config.getProperty(Configs.USERNAME), config.getProperty(Configs.PASSWORD), 
						transactional, ackMode);	
				
			} catch (NamingException | IOException e) {					
				e.printStackTrace();
			} 
			
			return null;				
	}
	
	public static JmsProducer loadJmsProducer(){
		try {
			
			Properties config=bundleToProps(Configs.PROD_BUNDLE_NAME);
			
			//Riferimento al servizio JNDI
			Context context = getInitialContext(config); 	
			
			//Ottengo un factory per la costruzione di una queue connection
			QueueConnectionFactory qcf = (QueueConnectionFactory) context.lookup(config.getProperty(Configs.CONNECTION_FACTORY_CFG_KEY));
			
			String destinationType=config.getProperty(Configs.DEST_TYPE_MODE_KEY);
			
			Destination destination=null;
			
			switch(destinationType){
				case Configs.QUEUE_TYPE:
					destination=(Queue)context.lookup(config.getProperty((Configs.QUEUE_CFG_KEY)));
					break;
				case Configs.TOPIC_TYPE:
					destination=(Queue)context.lookup(config.getProperty((Configs.TOPIC_CFG_KEY)));
					break;
			}
					
			boolean transactional= Boolean.parseBoolean(config.getProperty(Configs.TRANSACTIONAL_KEY));
			int ackMode=Integer.parseInt(config.getProperty(Configs.ACK_MODE_KEY));
			
			return new JmsProducer(qcf, destination, 
					config.getProperty(Configs.USERNAME), config.getProperty(Configs.PASSWORD), 
					transactional, ackMode);	
			
		} catch (NamingException | IOException e) {					
			e.printStackTrace();
		}	
		
		return null;	
	}
		
	private static Properties bundleToProps(String bundleName) throws IOException{
		InputStream cfgStream = JndiUtils.class.getResourceAsStream(bundleName);
		Properties props= new Properties();
		props.load(cfgStream);
		return props;
	}
	
	private static Context getInitialContext(Properties properties){
//		Properties properties = new Properties();
//		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
//		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		InitialContext jndiContext = null;
		try{
			// Get an initial context
		   jndiContext = new InitialContext(properties);       		      
		}catch(NamingException e){
			System.out.println("Context Error: "+e.getMessage());
		}
		return jndiContext;
	}	

}
