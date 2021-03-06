package corso.spring.messaging.demo.jms.queue.test;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.queue.SpringQueueConsumer;
import corso.spring.messaging.demo.jms.queue.SpringQueueProducer;

public class TestJmsQueueProducer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =new ClassPathXmlApplicationContext("context-config-jboss71.xml");
		SpringQueueProducer producer = (SpringQueueProducer) context.getBean("queueProducer");
		
		testSendOneMessage(producer, "messaggio prova Spring JMS");
		//testSendMessages(producer,40);
		
	}
	
	
public static void testSendMessages (SpringQueueProducer producer,int quanti){
		
		//SpringQueueProducer ms = new SpringQueueProducer();
		
		Random rnd = new Random();
				
		for (int i=0;i<quanti;i++){
			
			int secondi =rnd.nextInt(3000);
			
			try {
				Thread.sleep(secondi);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean sent = producer.sendTextMessage("messaggio "+i);
			
			if(sent)
				System.out.println("Text Message sent...");
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
