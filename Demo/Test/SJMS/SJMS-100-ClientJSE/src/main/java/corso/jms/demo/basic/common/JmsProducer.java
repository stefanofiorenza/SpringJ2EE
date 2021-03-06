package corso.jms.demo.basic.common;


import java.io.UnsupportedEncodingException;

import javax.jms.BytesMessage;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSession;

import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.config.Configs;
import corso.jms.demo.basic.config.DestinationType;


@Slf4j
public class JmsProducer extends JmsClient{
		
	private MessageProducer producer;
	
	
	public JmsProducer(ConnectionFactory connectionFactory, Destination destination,String destinationType, 
			String user, String pw, boolean sessionTransactional, int ackMode){		
		super(connectionFactory, destination,destinationType, user, pw, sessionTransactional,  ackMode);
		createProducerInternal();			
	}
	
//	public JmsProducer(ConnectionFactory connectionFactory, String destinationName, String user, String pw, 
//			 boolean sessionTransactional, int ackMode, DestinationType destType){			
//		super(connectionFactory, destinationName,user, pw, sessionTransactional,  ackMode,destType);
//		createProducerInternal();	
//	}
	
	private void createProducerInternal(){
		try{		
			
			switch (destinationType) {
			case Configs.QUEUE_TYPE:
				producer = ((QueueSession) session).createProducer((Queue) destination);
				break;
				
			case Configs.TOPIC_TYPE:
				producer = ((TopicSession) session).createPublisher((Topic) destination);
				break;
			}					
		}		
		catch (JMSException e) {
			e.printStackTrace();
		}	
	}
	
	public void sendManyTextMessage(int howMany, int howOftenIllegalMsg, int commitInterval) {
		try{
			int sent=0;
			
			for (int i=0; i<howMany; i++){
				
				if(howOftenIllegalMsg>0 && i%howOftenIllegalMsg==0){
					sendTextMessage("");
				}else{
					sendTextMessage("Message#"+i);
				}	
				sent++;				
				
				if(session.getTransacted() && commitInterval>0 && sent%commitInterval==0){
					//ConsumerUtils.testTransazioneInRollback(session);
					session.commit();
				}
			}
		}catch (JMSException e){
			e.printStackTrace();
		}
	
		
	}
	
	public void sendManyTextMessage(int howMany, int howOftenIllegalMsg){		
		sendManyTextMessage(howMany,  howOftenIllegalMsg,0);
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
