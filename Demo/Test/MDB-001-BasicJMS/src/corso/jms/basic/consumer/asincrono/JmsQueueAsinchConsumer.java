package corso.jms.basic.consumer.asincrono;


import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.basic.consumer.listener.TextMessageListener;

public class JmsQueueAsinchConsumer {

	private static final String DESTINATION = "queue/testQueue";	
	private static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
	private static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	
	private QueueConnection queueConnection;
	private QueueConnectionFactory qcf;
	private QueueSession queueSession;
	private Queue queueReference;
	private QueueReceiver receiver;
	private TextMessageListener listener; 
	
	public JmsQueueAsinchConsumer(){
		try{
			// creo il Listener per i messaggi
			listener = new TextMessageListener("QueueConsumer");
			
			//Riferimento al servizio JNDI
			Context context = getInitialContext(); 			
			
			//Ottengo un factory per la costruzione di una queue connection
			qcf = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY_JBOSS51);			
			
			//Ottengo un riferimento alla destination
			 queueReference = (Queue)context.lookup(JmsQueueAsinchConsumer.DESTINATION);
			
			 //Ottengo una connessione ad una destination di tipo queue
			queueConnection = qcf.createQueueConnection();
			
			//Creo una sessione non-transazionale di comunicazione con la coda
			//In questo caso il messaggio viene eliminato automaticamente dalla coda
			//(è la sessione che in background esegue l'acknowledge). Se il valore
			//fosse true, il messaggio verrebbe eliminato dalla coda solo dopo avere invocato
			//commit() o close() sulla sessione. 
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);			
			
			//Creo il consumer collegato a queue/testQueue			
			receiver = queueSession.createReceiver(queueReference);	
			
			// aggancio il consumer al listener
			receiver.setMessageListener(listener);
			queueConnection.start();
			
			// rimane collegato per 10 secondi
			Thread.sleep(10000);
			
			 System.out.println("Scollegato");
			// poi chiude la connessione e termina il thread
			closeCommunication();
			
		}
		catch (NamingException e) {
			System.out.println("Naming Exception "+e.getMessage());
		}
		catch (JMSException e) {
			System.out.println("JMS Exception "+e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void closeCommunication(){
		if (queueConnection != null && queueSession != null) {
			try {
				queueSession.close();
				queueConnection.close();
			}
			catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Context getInitialContext(){
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		InitialContext jndiContext = null;
		try{
			// Get an initial context
		   jndiContext = new InitialContext(properties);       		      
		}catch(NamingException e){
			System.out.println("Context Error: "+e.getMessage());
		}
		return jndiContext;
	}

	public static void main(String[] args) {
		JmsQueueAsinchConsumer queueConsumer = new JmsQueueAsinchConsumer();
		
		
	  
    }

}
