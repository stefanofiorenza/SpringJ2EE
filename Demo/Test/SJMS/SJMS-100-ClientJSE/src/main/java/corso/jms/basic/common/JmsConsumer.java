package corso.jms.basic.common;


import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.extern.slf4j.Slf4j;
import corso.jms.basic.config.DestinationType;
import corso.jms.basic.config.JBossUtils;
import corso.jms.basic.consumer.listener.TextMessageListener;


@Slf4j
public class JmsConsumer extends JmsClient{

	private MessageConsumer consumer;

	
	public JmsConsumer(ConnectionFactory connectionFactory, Destination destination, String user, String pw, 
		 boolean sessionTransactional, int ackMode){
		
		super(connectionFactory,  destination,user, pw, sessionTransactional,  ackMode);
		createConsumerInternal();
	}
	
	public JmsConsumer(ConnectionFactory connectionFactory, String destinationName, String user, String pw, 
			 boolean sessionTransactional, int ackMode, DestinationType destType){
		
			super(connectionFactory, destinationName,user, pw, sessionTransactional,  ackMode,destType);
			createConsumerInternal();
	}
	
	
	private void createConsumerInternal(){
		try{				
			//Creo un consumer collegato alla Destination sul Message Broker			
			consumer = session.createConsumer(destination);			
		}		
		catch (JMSException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	public void setTextMessageListener(TextMessageListener listener){	
		try {
			consumer.setMessageListener(listener);	
		} catch (JMSException e) {			
			e.printStackTrace();			
		} 
	}
	
	public void pollingForAllMessages(String exitMessage) throws JMSException{
		pollingForAllMessages(exitMessage,0);
	}
	
	public void pollingForAllMessages(String exitMessage, int commitInterval) throws JMSException{

		log.info("Star polling for messages..");			
		String msgContent="";
		
		int consumed=0;
		int acked=0;
		
		while(!msgContent.equals(exitMessage)){	
			
			if(commitInterval>0 && consumed!=0 && consumed%commitInterval==0){				
				msgContent=pollingForOneTextMessage();
				session.commit();
				acked++;
			}
			consumed++;			
		}				
		log.info("Polling ended for message: {} Consumed: {} Acked: {} ",msgContent,consumed,acked);
	}
	
	
	public String pollingForOneTextMessage() throws JMSException{
		Message message = consumer.receive(); //istruzione bloccante fino a che non ce n'e' un altro msg
		log.info("A message exists...");				
		return processMessage(message);		
	}
	
	
	private String processMessage(Message message) throws JMSException{
		String msgContent="";
		
		validateMessage(message);
		
		TextMessage textMessage = (TextMessage) message;
		msgContent=textMessage.getText();
		
		log.info("Received: "+msgContent);		
		return msgContent;
	}
	
	
	
	private void validateMessage(Message message) throws JMSException{
		
		if(message==null){
			throw new RuntimeException("Message received was null");
		}
		
		if (!(message instanceof TextMessage)) {
			throw new RuntimeException("Invalid type. Expected TextMessage Found "+message.getClass().getName());			
		}
		
		TextMessage textMessage = (TextMessage) message;
		String msgContent=textMessage.getText();
		
		if(msgContent==null || msgContent.equals("")){
			throw new RuntimeException("Found message with Empty content");
		}		
	}


}
