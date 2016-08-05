package corso.spring.messaging.demo.jms.transazioni.consumers;

import java.util.Random;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.hibernate.engine.query.OrdinalParameterDescriptor;
import org.springframework.jms.listener.SessionAwareMessageListener;

import corso.spring.messaging.demo.jms.common.Constants;
import corso.spring.messaging.demo.jms.common.MessageStoreUtil;

public class SpringSessionAwareMessageListener implements SpringMessageConsumer,SessionAwareMessageListener<ObjectMessage>,ExceptionListener{

	@Override
	public void onMessage(ObjectMessage message, Session session)
			throws JMSException,RuntimeException {

			
			ObjectMessage messageObj= (ObjectMessage)message;
			String categoria=messageObj.getStringProperty("categoria");
			int numeroOrdinale= messageObj.getIntProperty("ordinale");
			
			Object contenuto=messageObj.getObject();
			
			if(!categoria.equals("A")){
				System.out.println("La categoria non puo' essere diversa da A");
				session.rollback();
				throw new RuntimeException("La categoria non puo' essere diversa da A");
				
			}			
				
			System.out.println("\nRicevuto Messaggio:\n"+
								"Ordinale: "+numeroOrdinale+"\n"+
								"Categoria: "+categoria+"\n"+
								"Contenuto: "+contenuto);
			
			
			Random rnd = new Random();
			int tempoElaborazione=rnd.nextInt(1500);
			
			try {
				Thread.sleep((long)tempoElaborazione);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String fileName=categoria+numeroOrdinale;			
			MessageStoreUtil.saveMessage(Constants.CONSUMER_PATH, fileName, messageObj);
			
			
			if(numeroOrdinale==Constants.QUANTITA_MESSAGGI_ORDINATI){
				session.commit();
			}				
			
			
		
	}

	@Override
	public void onException(JMSException eccezione) {
		System.out.println("Eccezione: "+eccezione);
		eccezione.printStackTrace();
		
	}

}
