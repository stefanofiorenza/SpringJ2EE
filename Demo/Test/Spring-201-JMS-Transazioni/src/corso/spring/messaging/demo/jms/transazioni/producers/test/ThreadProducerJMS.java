package corso.spring.messaging.demo.jms.transazioni.producers.test;

import java.util.Random;

import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.transazioni.producers.SpringMessageProducer;



public class ThreadProducerJMS extends Thread{

	private int latenza;
	private String categoria;
	private int quantiMessaggi;
			
	public ThreadProducerJMS(String categoria, int latenza, int quantiMessaggi){
		this.categoria=categoria;
		this.latenza=latenza;
		this.quantiMessaggi=quantiMessaggi;
	}
	
	@Override
	public void run() {
		
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/transaction-context-config.xml");
		SpringMessageProducer producer = (SpringMessageProducer) context.getBean("springMessageProducer");
		testSendMessages(producer,this.quantiMessaggi);
		
	}
	
	
	
	public void testSendMessages (SpringMessageProducer producer,int numeroOrdinale){
		
		
		Random rnd = new Random();
		int secondi = rnd.nextInt(this.latenza);

		try {
			Thread.sleep(secondi);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean sent = producer.sendMessage("messaggio" + numeroOrdinale, this.categoria, numeroOrdinale);

		if (sent)
			System.out.println("Messaggio spedito correttamente");
		else
			System.out.println("Eccezione nella spedizione messaggio");
		
		
		
		
	}
	

}
