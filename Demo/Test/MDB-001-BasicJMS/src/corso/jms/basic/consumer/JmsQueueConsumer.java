package corso.jms.basic.consumer;


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

public class JmsQueueConsumer {

	private static final String DESTINATION = "queue/testQueue";	
	private static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
	private static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	
	public static final String EXIT_MESSAGE="closeConsumer";
	
	private QueueConnection queueConnection;
	private QueueConnectionFactory qcf;
	private QueueSession queueSession;
	private Queue queueReference;
	private QueueReceiver receiver;
	
	
	public JmsQueueConsumer(){
		try{
			//Riferimento al servizio JNDI
			Context context = getInitialContext(); 			
			//Ottengo un factory per la costruzione di una queue connection
			qcf = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY_JBOSS51);			
			//Ottengo un riferimento alla destination
			 queueReference = (Queue)context.lookup(JmsQueueConsumer.DESTINATION);
			//Ottengo una connessione ad una destination di tipo queue
			queueConnection = qcf.createQueueConnection();
			//Creo una sessione non-transazionale di comunicazione con la coda
			//In questo caso il messaggio viene eliminato automaticamente dalla coda
			//(è la sessione che in background esegue l'acknowledge). Se il valore
			//fosse true, il messaggio viene eliminato dalla coda solo dopo avere invocato
			//commit() o close() sulla sessione. 
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);			
			//Creo un consumer che leggerà messaggi da queue/testQueue			
			receiver = queueSession.createReceiver(queueReference);			
		}
		catch (NamingException e) {
			System.out.println("Naming Exception "+e.getMessage());
		}
		catch (JMSException e) {
			System.out.println("JMS Exception "+e.getMessage());
		}
		
	}
	
	public boolean receiveTextMessage(){
		try{
			queueConnection.start();
			System.out.println("In attesa di ricevere messaggio..");
			
			String exit="";
			
			while(!exit.equals(EXIT_MESSAGE)){
				Message message = receiver.receive(); //istruzione bloccante fino a che non ce n'e' un altro
		
				if(message != null) {
					System.out.println("A message exists...");
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						exit=textMessage.getText();
						System.out.println("Ricevuto: "+exit);
					}
				}else break;
			}	    
		}catch (JMSException e) {
	      System.out.println("JMS Exception "+e.getMessage());
	      return false;
	    }	    
	    return true;
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
		JmsQueueConsumer queueConsumer = new JmsQueueConsumer();
		
		
		queueConsumer.receiveTextMessage();	  
		
		queueConsumer.closeCommunication();
	  
    }

}
