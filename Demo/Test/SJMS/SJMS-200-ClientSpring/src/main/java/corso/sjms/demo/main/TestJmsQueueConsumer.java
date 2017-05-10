package corso.sjms.demo.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.sjms.demo.clients.JmsConsumerSpringImpl;
import corso.sjms.demo.clients.JmsProducerSpringImpl;
import corso.sjms.demo.common.JmsDemoUtils;
import corso.sjms.demo.common.XmlConfigs;

@Slf4j
public class TestJmsQueueConsumer {

	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	
	public static void main(String[] args) {
		
		//1) 
		ApplicationContext context =new ClassPathXmlApplicationContext(XmlConfigs.POLLING_QUEUE);
		
	}
	
	
	
	private static void testTemplate(ApplicationContext context){
		JmsConsumerSpringImpl consumer = context.getBean(XmlConfigs.CONSUMER_POLLING_Q,JmsConsumerSpringImpl.class);
		JmsProducerSpringImpl producer=  context.getBean(XmlConfigs.PRODUCER_QUEUE,JmsProducerSpringImpl.class);
		consumerThread(consumer);
		producerThread(producer);
	}
	
	
	private static void consumerActions(JmsConsumerSpringImpl consumer){		
		consumer.pollingForAllMessages(JmsDemoUtils.MESSAGE_EXIT, 5); //commit ogni 5 messaggi		
	}
	
	private static void producerActions(JmsProducerSpringImpl producer) {
		producer.sendManyTextMessage(20, 0);		
	}	
	
	
	private static void producerThread(JmsProducerSpringImpl producer){
		
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
		
	private static void consumerThread(JmsConsumerSpringImpl consumer){
		
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

}
