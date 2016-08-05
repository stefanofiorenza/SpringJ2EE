package corso.spring.messaging.demo.jms.test;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.jms.demo.consumers.SpringQueueConsumer01Polling;
import corso.spring.jms.demo.producers.SpringQueueProducer;

public class TestJmsQueueProducer {

	private static final int DELAY=100;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/jms/demo/config/jms-00ContextConfig.xml");
		SpringQueueProducer producer = (SpringQueueProducer) context.getBean("queueProducer");
		
		//testSendOneMessage(producer, "messaggio prova Spring JMS");
		testSendMessages(producer,200);
		
	}
	
	
public static void testSendMessages (SpringQueueProducer producer,int quanti){
		
		//SpringQueueProducer ms = new SpringQueueProducer();
		
		Random rnd = new Random();
				
		for (int i=0;i<quanti;i++){
			
			int secondi =rnd.nextInt(DELAY);
			
			try {
				Thread.sleep(secondi);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean sent = producer.sendTextMessage("messaggio "+i);
			
			if(sent)
				System.out.println("spedito messaggio:messaggio "+i);
			else
				System.out.println("Comunication problem...");	
		}
		
		
		
		
	}
	
	public static void testSendOneMessage (SpringQueueProducer producer,String messaggio){
		
	
		boolean sent = producer.sendTextMessage(messaggio);
		
		
		if(sent)
			System.out.println("Text Message sent...");
		else
			System.out.println("Comunication problem...");		
		
	}

}
