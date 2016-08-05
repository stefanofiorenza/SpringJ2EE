package corso.spring.messaging.demo.jms.transazioni.consumers.test;

import java.util.List;

import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.common.Constants;
import corso.spring.messaging.demo.jms.common.MessageStoreUtil;
import corso.spring.messaging.demo.jms.transazioni.consumers.SpringMessageConsumerPolling;
import corso.spring.messaging.demo.jms.transazioni.consumers.SpringMessageConsumerPollingTrans;

public class TestListenerConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ricezioneNonTransazionale();
		ricezioneTransazionale();
	}

	public static void ricezioneNonTransazionale(){
		
		try{
			System.out.println("Avvio ascolto consumer ..");
			ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/listener-config.xml");
			
			Thread.sleep(10000); //rimane in ascolto per 10 secondi
			System.out.println("Fine ricezione");
			System.exit(1);
		}catch (Exception ecc){
			ecc.printStackTrace();
		}
	}
	
	public static void ricezioneTransazionale(){
		
		try{
			System.out.println("Avvio ascolto consumer..");
			ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/listener-trans-config.xml");
			Thread.sleep(30000); //rimane in ascolto per 10 secondi
			System.out.println("Fine ricezione");
			System.exit(1);
		}catch (Exception ecc){
			ecc.printStackTrace();
		}
		
		
	}
}
