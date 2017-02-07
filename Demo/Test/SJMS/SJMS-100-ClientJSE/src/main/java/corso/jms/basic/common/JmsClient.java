package corso.jms.basic.common;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import corso.jms.basic.config.DestinationType;

public class JmsClient {

	protected Connection connection;
	protected Session session;	
	protected Destination destination;
	
	public JmsClient(ConnectionFactory connectionFactory, Destination destination,String user, String pw, 
			boolean sessionTransactional, int ackMode){
		try{
			
			//Apro una connessione Jms ottenendola dal Pool (Factory)
			this.connection = connectionFactory.createConnection(user,pw);
			
			//inizializza destination
			this.destination=destination;
			
			//Creo una sessione JMS (transactional, ackMode)			
			this.session = connection.createSession(sessionTransactional, ackMode);				
		}		
		catch (JMSException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void startConnection(){
		try{
			connection.start();
		}catch (JMSException e){
			e.printStackTrace();
		}		
	}
	
	public void stopConnection(){
		try{
			connection.stop();
		}catch (JMSException e){
			e.printStackTrace();
		}		
	}
	
		
	public void closeCommunication(){
		if (connection != null && session != null) {
			try {
				session.close();
				connection.close();
			}
			catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public JmsClient(ConnectionFactory connectionFactory, String destinationName,  String user, String pw, 
//	boolean sessionTransactional, int ackMode,DestinationType destType){
//try{
//	
//	//Apro una connessione Jms ottenendola dal Pool (Factory)
//	this.connection = connectionFactory.createConnection(user,pw);
//	
//	//Creo una sessione JMS (transactional, ackMode)			
//	this.session = connection.createSession(sessionTransactional, ackMode);	
//	
//	//si collega a destination per id (gia esistente sul Broker)
//	switch(destType){
//		case QUEUE:
//		destination=session.createQueue(destinationName);
//		break;
//		case TOPIC:
//		destination=session.createTopic(destinationName);
//		break;
//	}
//}		
//catch (JMSException e) {
//	e.printStackTrace();
//}		
//}
}
