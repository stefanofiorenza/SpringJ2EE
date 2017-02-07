package corso.jms.basic.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.common.JmsProducer;

public class TestConfigs {

	
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	
	public static void main(String[] args) throws IOException {
		
		Properties consumerProps= bundleToProps("/consumer.properties");		
		Properties producerProps= bundleToProps("/producer.properties");		
		JmsConsumer consumer=JndiUtils.loadJmsConsumer(consumerProps);
		JmsProducer producer=JndiUtils.loadJmsProducer(producerProps);
		consumerThread(consumer);
		producerThread(producer);
	}
	
	
	
	public static void consumerThread(JmsConsumer consumer){
		
		executor.submit(new Runnable() {	
			
			@Override
			public void run() {
				consumerActions(consumer);				
			}
		});		
	}
	
	public static void producerThread(JmsProducer producer){
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				producerActions(producer);				
			}			
		});				
	}
	
	private static Properties bundleToProps(String bundleName) throws IOException{
		InputStream cfgStream = TestConfigs.class.getResourceAsStream(bundleName);
		Properties props= new Properties();
		props.load(cfgStream);
		return props;
	}
	
	private static void producerActions(JmsProducer producer) {		
		producer.startConnection();
		producer.sendTextMessage("test");		
	}
	
	private static void consumerActions(JmsConsumer consumer){
		try{			
			consumer.startConnection();
			consumer.pollingForOneTextMessage();
		}catch (JMSException e){
			e.printStackTrace();
		}
		
	}

}
