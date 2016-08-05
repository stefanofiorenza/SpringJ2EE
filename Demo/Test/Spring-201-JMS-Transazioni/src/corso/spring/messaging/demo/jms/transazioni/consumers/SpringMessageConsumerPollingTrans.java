package corso.spring.messaging.demo.jms.transazioni.consumers;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;

import corso.spring.messaging.demo.jms.common.Constants;

public class SpringMessageConsumerPollingTrans implements SpringMessageConsumer{

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
	
	
	public List<ObjectMessage> receiveMessages(){
					
			SessionCallback<List<ObjectMessage>> handlerSessione = new SessionHandler(this.destination,Constants.QUANTITA_MESSAGGI_ORDINATI); 
			List<ObjectMessage> risultati= jmsTemplate.execute(handlerSessione, true);//true=recupera una nuova Connection
			return risultati;						
	}
	
	
	private class SessionHandler implements SessionCallback<List<ObjectMessage>>{

		private Destination destinazione;
		private int indiceFineTransazione;
		
		public SessionHandler(Destination destinazione,int indiceFineTransazione){
			this.destinazione=destinazione;
			this.indiceFineTransazione=indiceFineTransazione;
		}
		
		
		@Override
		public List<ObjectMessage> doInJms(Session session) throws JMSException {
			// TODO Auto-generated method stub
			List<ObjectMessage> messaggi = new ArrayList<ObjectMessage>();
			try{	
				MessageConsumer consumer= session.createConsumer(this.destinazione);
				int numeroOrdinale=0;
				while(numeroOrdinale!=this.indiceFineTransazione){
					
					ObjectMessage messageObj= (ObjectMessage)consumer.receive();
					String categoria=messageObj.getStringProperty("categoria");
					
//					if(!categoria.equals("A")){
//						throw new RuntimeException("La categoria non puo' essere diversa da A");
//					}
					numeroOrdinale= messageObj.getIntProperty("ordinale");
					Object contenuto=messageObj.getObject();
					System.out.println("\nRicevuto Messaggio:\n"+
										"Ordinale: "+numeroOrdinale+"\n"+
										"Categoria: "+categoria+"\n"+
										"Contenuto: "+contenuto);
					messaggi.add(messageObj);
				}
				
				session.commit();
				
			}catch (JMSException ecc){
				ecc.printStackTrace();			
			}
			
			return messaggi;
		}
		
	}
	
	
}
