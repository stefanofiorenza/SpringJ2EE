package corso.jms.demo.basic.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.demo.basic.common.JmsClient;
import corso.jms.demo.basic.common.JmsConsumer;
import corso.jms.demo.basic.common.JmsProducer;


public class JndiUtils {

	
	public static JmsConsumer loadJmsConsumer(String name){								
		return (JmsConsumer)loadCommonFields(name,Configs.CONSUMER);				
	}
	
	public static JmsProducer loadJmsProducer(){
		return (JmsProducer)loadCommonFields("Producer",Configs.PRODUCER);
	}
		
	private static JmsClient loadCommonFields(String name,String clientType){
			
		JmsClient jmsClient=null;
		
		try{
			
			ConnectionFactory genericConnFactory=null;
			Destination destination=null;
						
			Properties config=bundleToProps(Configs.PROD_BUNDLE_NAME);			
			Context context = getInitialContext(config); 	
					
			String destinationType=config.getProperty(Configs.DEST_TYPE_MODE_KEY);
	
			switch(destinationType){
				case Configs.QUEUE_TYPE:
					genericConnFactory = (QueueConnectionFactory) context.lookup(config.getProperty(Configs.QUEUE_CONNECTION_FACTORY_CFG_KEY));					
					destination=(Queue)context.lookup(config.getProperty((Configs.QUEUE_CFG_KEY)));
					break;
				
				case Configs.TOPIC_TYPE:
					genericConnFactory =(TopicConnectionFactory) context.lookup(config.getProperty(Configs.TOPIC_CONNECTION_FACTORY_CFG_KEY));
					destination=(Topic)context.lookup(config.getProperty((Configs.TOPIC_CFG_KEY)));
					break;
			}
					
			boolean transactional= Boolean.parseBoolean(config.getProperty(Configs.TRANSACTIONAL_KEY));
			int ackMode=Integer.parseInt(config.getProperty(Configs.ACK_MODE_KEY));
			
			switch(clientType){
				case Configs.PRODUCER:
					jmsClient= new JmsProducer(genericConnFactory, destination, destinationType,
						config.getProperty(Configs.USERNAME), config.getProperty(Configs.PASSWORD), 
						transactional, ackMode);	
					break;
				
				case Configs.CONSUMER:
					jmsClient= new JmsConsumer(name,genericConnFactory, destination, destinationType,
						config.getProperty(Configs.USERNAME), config.getProperty(Configs.PASSWORD), 
						transactional, ackMode);	
					break;
				
				default: throw new RuntimeException("Client type not supported");
			}		
			
			
		
		} catch (NamingException | IOException e) {					
			e.printStackTrace();
		}	
		
		return jmsClient;
	}
	
	
	private static Properties bundleToProps(String bundleName) throws IOException{
		InputStream cfgStream = JndiUtils.class.getResourceAsStream(bundleName);
		Properties props= new Properties();
		props.load(cfgStream);
		return props;
	}
	
	private static Context getInitialContext(Properties properties){
		InitialContext jndiContext = null;
		try{
		   jndiContext = new InitialContext(properties);       		      
		}catch(NamingException e){
			System.out.println("Context Error: "+e.getMessage());
		}
		return jndiContext;
	}	

}
