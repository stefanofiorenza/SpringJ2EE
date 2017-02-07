package corso.spring.messaging.demo.jms.test;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.jms.demo.producers.SpringQueueProducer;

public class TestJmsQueueProducer {

	private static Random rnd = new Random();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/jms/demo/config/jms-00ContextConfig.xml");
		SpringQueueProducer producer = (SpringQueueProducer) context.getBean("queueProducer");
		
		//testSendOneMessage(producer, "messaggio prova Spring JMS");
		//testSendMessages(producer,40);
		//producer.sendTextMessage("messaggio prova Spring JMS");
		
		testSendMessages(producer,100,10);
	}
	
	
	public static void testSendMessages (SpringQueueProducer producer,int quanti, int howOftenIllegalMsg){
					
		for (int i=0;i<quanti;i++){
			
			slowCurrentThreadForRndMillis(10);
			
			if(howOftenIllegalMsg>0 && i%howOftenIllegalMsg==0){
				producer.sendTextMessage("");
			}else{
				producer.sendTextMessage("messaggio "+i);
			}			
		}		
	}
	
//public static void testSendOneMessage (SpringQueueProducer producer,String messaggio){	
//	producer.sendTextMessage(messaggio);				
//}

	private static void slowCurrentThreadForRndMillis(int maxSleepTime){
		
		int millis =rnd.nextInt(maxSleepTime);
		
		try {
			Thread.sleep(millis);
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}



}
