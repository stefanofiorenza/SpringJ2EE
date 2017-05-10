package corso.jms.demo.basic.main;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.common.JmsConsumer;
import corso.jms.demo.basic.common.JmsProducer;
import corso.jms.demo.basic.config.Configs;
import corso.jms.demo.basic.config.JndiUtils;

@Slf4j
public class Sjms100Test01Polling {

	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
		
	public static void main(String[] args) throws IOException {
				
		JmsProducer producer=JndiUtils.loadJmsProducer();
		
		JmsConsumer consumer1=JndiUtils.loadJmsConsumer("Consumer1");
		//JmsConsumer consumer2=JndiUtils.loadJmsConsumer("Consumer2");
			
		producerThread(producer);		
		consumerThread(consumer1);
		//consumerThread(consumer2);
	}
	
	private static void producerActions(JmsProducer producer) {		
		producer.startConnection();
		//producer.sendTextMessage("test");	
		
		producer.sendManyTextMessage(20, 0,5);
		producer.closeCommunication();
	}
	
	
	private static void consumerActions(JmsConsumer consumer){
		try{			
			consumer.startConnection();
			
			//consumer.pollingForOneTextMessage();			
			
			consumer.pollingForAllMessages(Configs.MESSAGE_EXIT, 5); //commit ogni 5 messaggi
			consumer.closeCommunication();
		}catch (Exception e){
			e.printStackTrace();
		}	
	}
		
	
	
	private static void consumerThread(JmsConsumer consumer){
		
		executor.submit(new Runnable() {				
			@Override
			public void run() {
				try{
					consumerActions(consumer);	
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}								
			}
		});		
	}
	
	private static void producerThread(JmsProducer producer){
		
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				try{
					producerActions(producer);	
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}
							
			}			
		});				
	}
	

	
	
}
