package corso.jms.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.common.JmsProducer;
import corso.jms.basic.config.JndiUtils;

public class TestConfigs {

	
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	
	public static void main(String[] args) throws IOException {
				
		JmsConsumer consumer=JndiUtils.loadJmsConsumer();
		JmsProducer producer=JndiUtils.loadJmsProducer();
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
