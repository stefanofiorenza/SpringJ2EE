package corso.jms.basic.common;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
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
public class JmsProducer extends JmsClient{
		
	private MessageProducer producer;
	
	
	public JmsProducer(ConnectionFactory connectionFactory, Destination destination, String user, String pw, 
			 boolean sessionTransactional, int ackMode){		
		super(connectionFactory,  destination,user, pw, sessionTransactional,  ackMode);
		createProducerInternal();			
	}
	
	public JmsProducer(ConnectionFactory connectionFactory, String destinationName, String user, String pw, 
			 boolean sessionTransactional, int ackMode, DestinationType destType){			
		super(connectionFactory, destinationName,user, pw, sessionTransactional,  ackMode,destType);
		createProducerInternal();	
	}
	
	private void createProducerInternal(){
		try{			
			//Creo un producer collegato alla Destination sul Message Broker			
			producer = session.createProducer(destination);			
		}		
		catch (JMSException e) {
			e.printStackTrace();
		}	
	}
	
	public void sendManyTextMessage(int howMany, int howOftenIllegalMsg){
		
		for (int i=0; i<howMany; i++){
			if(howOftenIllegalMsg!=0 && i%howOftenIllegalMsg==0){
				sendTextMessage("");
			}else{
				sendTextMessage("Message#"+i);
			}			
		}
	}
	
	public void sendTextMessage(String msgContent){
	
		try{
			TextMessage message = session.createTextMessage();
			message.setText(msgContent);
			
			sendMessageAndLog(message);
			
		}catch (JMSException e) {
			e.printStackTrace();						
		}					   
	}
	
	public void sendObjectMessage(String msgContent){
		
		try{
			ObjectMessage message = session.createObjectMessage();
			message.setObject(asJsonObject(msgContent));
			
			sendMessageAndLog(message);
			
		}catch (JMSException e) {
			e.printStackTrace();						
		}					   
	}

	public void sendBinaryMessage(String msgContent){
		
		try{
			
			BytesMessage message = session.createBytesMessage();
			message.writeBytes(asJsonObject(msgContent).getBytes("UTF-8"));
			
			sendMessageAndLog(message);
			
		}catch (JMSException | UnsupportedEncodingException e) {
			e.printStackTrace();						
		}					   
	}
	
	public void sendTextMessageWithHeaders(String msgContent){		
		throw new UnsupportedOperationException("To be implemented");					   
	}
	
	private void sendMessageAndLog(Message message) throws JMSException{
		//Spedisco
		log.info("Sending msg with content: [ {} ] ...",message);
		producer.send(message);
		log.info("Message with content: [ {} ] Sent",message);
	}

	private String asJsonObject(String data){
		return "{data: \""+data+"\"}";
	}
}
