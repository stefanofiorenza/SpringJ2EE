package corso.spring.messaging.demo.jms.transazioni.consumers;



import java.sql.Timestamp;
import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringMessageConsumerPolling implements SpringMessageConsumer{

	private JmsTemplate jmsTemplate;
	private Destination destination;
	
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
	public ObjectMessage receiveMessage(){
		ObjectMessage messageObj=null;
		
		try{
			messageObj=(ObjectMessage)jmsTemplate.receive();
			String categoria=messageObj.getStringProperty("categoria");
			
			if(!categoria.equals("A")){
				throw new RuntimeException("La categoria non puo' essere diversa da A");
			}
			int numeroOrdinale= messageObj.getIntProperty("ordinale");
			Object contenuto=messageObj.getObject();
			System.out.println("\nRicevuto Messaggio:\n"+
								"Ordinale: "+numeroOrdinale+"\n"+
								"Categoria: "+categoria+"\n"+
								"Contenuto: "+contenuto);
			
		}catch (Exception ecc){
			ecc.printStackTrace();			
		}
		return messageObj;
	}
	
	
}
