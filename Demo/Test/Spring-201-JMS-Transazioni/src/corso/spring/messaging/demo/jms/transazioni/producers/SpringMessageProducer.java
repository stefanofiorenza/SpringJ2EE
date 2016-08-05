package corso.spring.messaging.demo.jms.transazioni.producers;



import java.sql.Timestamp;
import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringMessageProducer {

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
	
	
	public boolean sendMessage(String messaggio, String categoriaHeader, int numeroOrdinale){
		try{
			MessageCreatorImpl creatoreMessaggi = new MessageCreatorImpl(messaggio, categoriaHeader, numeroOrdinale);
			jmsTemplate.send(destination, creatoreMessaggi);
			
		}catch (Exception ecc){
			ecc.printStackTrace();
			return false;
		}
		return true;
	}
	
	private class MessageCreatorImpl implements MessageCreator{

		private String contenutoMessaggio;
		private String categoria;
		private int numeroOrdinale;
		
		public MessageCreatorImpl(String contenutoMessaggio,String categoria, int numeroOrdinale){
			this.contenutoMessaggio=contenutoMessaggio;
			this.categoria= categoria;
			this.numeroOrdinale=numeroOrdinale;
		}
		
		@Override
		public Message createMessage(Session sessione) throws JMSException {
			// TODO Auto-generated method stub

			ObjectMessage messaggio=sessione.createObjectMessage();
			
			//headers
			messaggio.setStringProperty("categoria", this.categoria);
			Date adesso = new Date();
			messaggio.setJMSTimestamp(adesso.getTime()); //timestamp spedizione
			messaggio.setIntProperty("ordinale", this.numeroOrdinale);
			
			//messaggio.setJMSExpiration(30000); //dura nella coda 30 secondi
			//messaggio.setJMSPriority(1); //priorita'
			
			
			//contenuto
			messaggio.setObject(this.contenutoMessaggio);
						
			System.out.println("\nProdotto messaggio :\n"+
								"categoria "+this.categoria+"\n"+
								"ordinale "+this.numeroOrdinale+"\n"+
								"contenuto "+this.contenutoMessaggio+"\n");
			return messaggio;
		}
		
	}
}
