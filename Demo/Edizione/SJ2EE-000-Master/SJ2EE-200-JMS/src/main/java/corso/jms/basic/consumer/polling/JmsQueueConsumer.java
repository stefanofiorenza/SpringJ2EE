package corso.jms.basic.consumer.polling;


import java.util.Properties;

import javax.jms.ConnectionFactory;
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

import corso.jms.basic.consumer.common.JBossJndiUtils;

public class JmsQueueConsumer {

	
	
	
	
	public static final String EXIT_MESSAGE="closeConsumer";
	
	private QueueConnection queueConnection;
	private QueueConnectionFactory qcf;
	private QueueSession queueSession;
	private Queue queueReference;
	private QueueReceiver receiver;
	
	
	public JmsQueueConsumer(){
		try{
			//Riferimento al servizio JNDI
			Context context = JBossJndiUtils.getInitialContext(); 			
			//Ottengo un factory per la costruzione di una queue connection
			qcf= getFactory (context,JBossJndiUtils.CONNECTION_FACTORY_JBOSS71);
		
			 queueReference = (Queue)context.lookup(JBossJndiUtils.QUEUE_JBOSS71);
			 
			//Ottengo una connessione ad una destination di tipo queue
			queueConnection = qcf.createQueueConnection("testuser","testuserpw");
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
			e.printStackTrace();
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	
	  private QueueConnectionFactory getFactory (Context ctx, String jndiName) {
		    try{
		      System.out.println("\nAttempting to lookup: " + jndiName);
		      QueueConnectionFactory rtnFactory  = (QueueConnectionFactory) ctx.lookup(jndiName);
		      System.out.println("\nSuccessfully obtained ConnectionFactory from JBoss server");
		      System.out.println(rtnFactory);
		      return rtnFactory;
		    } catch (Exception e) {
		      System.out.println("Exception Message: " + e.getMessage());
		      System.out.println("Exception Cause:   " + e.getCause());
		      return null;
		    }
	}
	

	public boolean receiveTextMessage(){
		try{
			queueConnection.start();
			System.out.println("In attesa di ricevere messaggio..");
			
			String exit="";
			
			while(!exit.equals(EXIT_MESSAGE)){
				Message message = receiver.receive(); //istruzione bloccante fino a che non ce n'e' un altro
				System.out.println("A message exists...");
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					exit=textMessage.getText();
					System.out.println("Ricevuto: "+exit);
				}
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
	


	public static void main(String[] args) {
		JmsQueueConsumer queueConsumer = new JmsQueueConsumer();
		
		
		queueConsumer.receiveTextMessage();	  
		
		queueConsumer.closeCommunication();
	  
    }

}
