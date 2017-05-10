package corso.jms.demo.basic.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.common.JmsConsumer;
import corso.jms.demo.basic.common.JmsProducer;
import corso.jms.demo.basic.config.JndiUtils;
import corso.jms.demo.basic.listeners.TextMessageListener;

@Slf4j
public class Sjms100Test02EventDriven {

	
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) {		
		
		JmsProducer producer=JndiUtils.loadJmsProducer();
		producerThread(producer);
		consumerThread("ED-ConsumerName1", 30000L);
	
		//startConsumer("ED-ConsumerName2", 30000L);
		
	
	
    }
	
	private static void producerActions(JmsProducer producer) {		
		producer.startConnection();
		//producer.sendTextMessage("test");	
		
		producer.sendManyTextMessage(20, 5);
		producer.closeCommunication();
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
	

	private static void consumerThread(String consumerName, long timeBeforeDisconnect){
		
		executor.submit(new Runnable() {				
			@Override
			public void run() {
				try{					
					consumerActions(consumerName, timeBeforeDisconnect);	
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}								
			}
		});		
	}
	
	private static void consumerActions(String consumerName, long timeBeforeDisconnect){
		try {
			
			JmsConsumer consumer =JndiUtils.loadJmsConsumer(consumerName);
			consumer.setTextMessageListener(new TextMessageListener());
			consumer.startConnection();
					
			// Rimane in attesa per 30 secondi...
		    Thread.sleep(timeBeforeDisconnect);
	            
            log.info("Scollegato");
            consumer.closeCommunication();
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();		
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
}
