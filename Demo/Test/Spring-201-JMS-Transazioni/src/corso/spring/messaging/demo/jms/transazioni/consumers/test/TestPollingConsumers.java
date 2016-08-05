package corso.spring.messaging.demo.jms.transazioni.consumers.test;

import java.io.PrintWriter;
import java.util.List;

import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.common.Constants;
import corso.spring.messaging.demo.jms.common.MessageStoreUtil;
import corso.spring.messaging.demo.jms.transazioni.consumers.SpringMessageConsumerPolling;
import corso.spring.messaging.demo.jms.transazioni.consumers.SpringMessageConsumerPollingTrans;
import corso.spring.messaging.demo.jms.transazioni.producers.test.TestTransactionalScenario;



public class TestPollingConsumers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//MessageStoreUtil.cleanQueue(10);
		//ricezioneNonTransazionale();
		ricezioneTransazionale();
	}
	
	public static void ricezioneNonTransazionale(){
		
		try{
			ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/transaction-context-config.xml");
			SpringMessageConsumerPolling consumer = (SpringMessageConsumerPolling) context.getBean("simpleMessageConsumer");
			System.out.println("in attesa di ricezione");
			ObjectMessage messaggio=consumer.receiveMessage();
			System.out.println("Fine ricezione");
			
		}catch (Exception ecc){
			ecc.printStackTrace();
		}
	}
	
	public static void ricezioneTransazionale(){
		
		try{
			ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/transaction-context-config.xml");
			SpringMessageConsumerPollingTrans consumer = (SpringMessageConsumerPollingTrans) context.getBean("sessionTransMessageConsumer");
			System.out.println("in attesa di ricezione");
			List<ObjectMessage> messaggi=consumer.receiveMessages();
			int indice=0;
			for (ObjectMessage messaggio : messaggi){
				indice++;
				String fileName=messaggio.getStringProperty("categoria")+indice;
				MessageStoreUtil.saveMessage(Constants.CONSUMER_PATH,fileName,messaggio);			
			}			
			System.out.println("Fine ricezione");
			
		}catch (Exception ecc){
			ecc.printStackTrace();
		}
		
		
	}
	
	
	public void ricezioneOrdinata(){
		
	}
	

}
