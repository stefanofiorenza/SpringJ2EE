package corso.jms.basic.producer;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;



import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.jms.basic.consumer.polling.JmsQueueConsumer;

public class JmsQueueProducer {

	private static final String DESTINATION = "queue/testQueue";
	static final String CONNECTION_FACTORY_JBOSS423="QueueConnectionFactory";
	static final String CONNECTION_FACTORY_JBOSS51="ConnectionFactory";
	
	
	
	private QueueConnection queueConnection;
	private QueueConnectionFactory qcf;
	private QueueSession queueSession;
	private Queue queueReference;
	private QueueSender sender;
	
	
	public static void main(String[] args) {
		
		//testSendOneMessage("Primo messaggio");
		//testSendOneMessage("Secondo messaggio");
		//testSendOneMessage("Terzo messaggio");
		testSendMessages(10);
		testSendOneMessage(JmsQueueConsumer.EXIT_MESSAGE);
		
		
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
	
	public JmsQueueProducer(){
		try{
			//Riferimento al servizio JNDI
			Context context = getInitialContext(); 
			
			//Ottengo un factory per la costruzione di una queue connection
			qcf = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY_JBOSS51);	
			
			//Ottengo un riferimento alla destination
			 queueReference = (Queue)context.lookup(JmsQueueProducer.DESTINATION);
			 
			//Ottengo una connessione ad una destination di tipo queue
			queueConnection = qcf.createQueueConnection();
			
			
			//Creo una sessione non-transazionale di comunicazione con la coda:
			//In questo caso è la sessione che si occupa di fare l'ack sul messaggio
			//permettendo l'invio dello stesso. Nel caso in cui il valore fosse true,
			//il messaggio non viene inviato fin tanto che non venga eseguita una commit
			//sulla sessione o una chiusura della stessa.
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			
			//Creo un producer che spedirà messaggi su queue/testQueue
			sender = queueSession.createSender(queueReference);			
		}
		catch (NamingException e) {
			System.out.println("Naming Exception "+e.getMessage());
		}
		catch (JMSException e) {
			System.out.println("JMS Exception "+e.getMessage());
		}		
	}
	
	public boolean sendObjectMessage(Serializable obj){
		try{			
			queueConnection.start();
			//Creo un messaggio 
			ObjectMessage message = queueSession.createObjectMessage();
			message.setObject(obj);
			
			//Spedisco
			sender.send(message);
			//System.out.println("Message sent.");
		}catch (JMSException e) {
			System.out.println("JMS Exception "+e.getMessage());
			return false;
		}
		return true;
		
	}
	public boolean sendTextMessage(String text){
		try{
			//Creo un messaggio di testo
			TextMessage message = queueSession.createTextMessage();
			message.setText(text);
			//Spedisco
			sender.send(message);
			//System.out.println("Message sent.");
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
				
			}
		}
	}
	
	
	
	public static void testSendMessages (int quanti){
		
		JmsQueueProducer ms = new JmsQueueProducer();
		
		Random rnd = new Random();
				
		for (int i=0;i<quanti;i++){
			
			int secondi =rnd.nextInt(3000);
			
			try {
				Thread.sleep(secondi);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean sent = ms.sendTextMessage("messaggio "+i);
			
			if(sent)
				System.out.println("Text Message sent...");
			else
				System.out.println("Comunication problem...");	
		}
		
		
		ms.closeCommunication();
		
	}
	
	public static void testSendOneMessage (String messaggio){
		
		JmsQueueProducer ms = new JmsQueueProducer();
		boolean sent = ms.sendTextMessage(messaggio);
		
		
		if(sent)
			System.out.println("Text Message sent...");
		else
			System.out.println("Comunication problem...");		
		ms.closeCommunication();
	}
}
