package corso.spring.messaging.demo.jms.common;

import java.io.PrintWriter;

import javax.jms.ObjectMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.transazioni.consumers.SpringMessageConsumerPolling;
import corso.spring.messaging.demo.jms.transazioni.consumers.SpringMessageConsumerPollingTrans;

public class MessageStoreUtil {

	public static void saveMessage(String path,String fileName, ObjectMessage messaggio){
		
		try{
			String categoria=messaggio.getStringProperty("categoria");
			int ordinale= messaggio.getIntProperty("ordinale");
			String contenuto= messaggio.getObject().toString();
			PrintWriter out =  new PrintWriter(path+"/"+fileName+".txt");
			out.print("\nMessaggio:\n ordine: "+ordinale+"\n categoria: "+categoria+"\n Contenuto: "+contenuto);
			out.close();
		}catch (Exception ecc){
			ecc.printStackTrace();
		}
		
	}
	
	public static void logProducer(String path,String fileName, String idMessaggio,String categoria,String contenuto){
		
		try{
			PrintWriter out =  new PrintWriter(path+"/"+fileName+".txt");
			out.print("\nMessaggio:\n ordine: "+idMessaggio+"\n categoria: "+categoria+"\n Contenuto: "+contenuto);
			out.close();
		}catch (Exception ecc){
			ecc.printStackTrace();
		}
		
	}
	
	public static void cleanQueue(int cycles){
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/messaging/demo/jms/config/transaction-context-config.xml");
		SpringMessageConsumerPolling consumer = (SpringMessageConsumerPolling) context.getBean("simpleSpringMessageConsumer");
		for(int i=0;i<cycles;i++){
			consumer.receiveMessage();
		}	
	}
}
