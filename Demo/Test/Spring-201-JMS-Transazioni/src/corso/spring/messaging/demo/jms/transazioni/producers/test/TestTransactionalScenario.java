package corso.spring.messaging.demo.jms.transazioni.producers.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.common.Constants;
import corso.spring.messaging.demo.jms.common.MessageStoreUtil;
import corso.spring.messaging.demo.jms.transazioni.producers.SpringMessageProducer;

;

public class TestTransactionalScenario {


	public static final int NUMBER_OF_THREDS=10;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1) Scenario transazionale (tutti e 5 o nessuno)
		//testTransactionalMessagesOK();
		testTransactionalMessagesException();
		
		//2) Scenario Ordinale (Processati nell'ordine giusto)
		//testOrderedMessages();
 
	}
	
	public static void testTransactionalMessagesOK(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/transaction-context-config.xml");
		SpringMessageProducer producer = (SpringMessageProducer) context.getBean("springMessageProducer");
	
		for (int i=1;i<=Constants.QUANTITA_MESSAGGI_ORDINATI; i++){
			String contenuto="Un messaggio"+i;
			String messageId=""+i;
			String categoria="A";
			producer.sendMessage(contenuto, categoria,i);
			MessageStoreUtil.logProducer(Constants.PRODUCER_PATH, "spedito"+i, messageId,categoria,contenuto);
		}
		System.out.println("Fine trasmissione");
	}
	
	public static void testTransactionalMessagesException(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/transaction-context-config.xml");
		SpringMessageProducer producer = (SpringMessageProducer) context.getBean("springMessageProducer");
	
		for (int i=1;i<=Constants.QUANTITA_MESSAGGI_ORDINATI; i++){
			String contenuto="Un messaggio"+i;
			String messageId=""+i;
			String categoria="A";
			if(i%5==0){ //i multipli di 5 daranno eccezione per categoria!="A"
				categoria="B";
			}
			producer.sendMessage(contenuto, categoria,i);
			MessageStoreUtil.logProducer(Constants.PRODUCER_PATH, "spedito"+i, messageId,categoria,contenuto);
		}
			
		
		System.out.println("Fine trasmissione");
	}
	
	public static void testOrderedMessages(){
		
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREDS);
		
		for (int i=1;i<=Constants.QUANTITA_MESSAGGI_ORDINATI; i++){
			Runnable workerA = new ThreadProducerJMS("A",2000,i);
			executor.execute(workerA);
		}
			
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {

		}
		System.out.println("Finished all threads");
	}

}
