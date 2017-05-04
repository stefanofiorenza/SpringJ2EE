package corso.jms.demo.basic.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.common.JmsConsumer;
import corso.jms.demo.basic.common.JmsProducer;
import corso.jms.demo.basic.config.JndiUtils;
import corso.jms.demo.basic.consumer.listener.TextMessageListener;

@Slf4j
public class Sjms100Test02EventDriven {

	
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) {		
		
		JmsProducer producer=JndiUtils.loadJmsProducer();
		producerThread(producer);
		
		startConsumer("ED-ConsumerName1","QueueConsumerListener1", 30000L);
		//startConsumer("ED-ConsumerName2","QueueConsumerListener2", 30000L);
		
		//startConsumer("ED-ConsumerName1","TopicConsumerListener1", 30000L);
		//startConsumer("ED-ConsumerName2","TopicConsumerListener2", 30000L);
	
    }
	
	private static void producerActions(JmsProducer producer) {		
		producer.startConnection();
		//producer.sendTextMessage("test");	
		
		producer.sendManyTextMessage(20, 19);
		producer.closeCommunication();
	}
	
	private static void producerThread(JmsProducer producer){
		
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				producerActions(producer);				
			}			
		});				
	}

	
	private static void startConsumer(String consumerName, String messageListenerName,long timeBeforeDisconnect){
		try {
			
			JmsConsumer consumer =JndiUtils.loadJmsConsumer(consumerName);
			consumer.setTextMessageListener(new TextMessageListener(messageListenerName));
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
