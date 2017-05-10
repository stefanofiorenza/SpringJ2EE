package corso.sjms.demo.common;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JmsDemoUtils {

	public static final String MESSAGE_EXIT="CLOSE";
	
	public static void validateMessage(Message message) {
		
		if(message==null){
			throw new RuntimeException("Message received was null");
		}
		
		if (!(message instanceof TextMessage)) {
			throw new RuntimeException("Invalid type. Expected TextMessage Found "+message.getClass().getName());			
		}
		
		TextMessage textMessage = (TextMessage) message;	
		
		String msgContent=extractTextPayload(textMessage);
		
		
		if(msgContent==null || msgContent.equals("")){
			throw new RuntimeException("Found message with Empty content");
		}		
	}
	
	public static void ackMessage(Message message){
		try{
			message.acknowledge();
		}catch(JMSException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public static String extractTextPayload(TextMessage message) {
		String msgContent="";
		try{
			msgContent=message.getText();
		}catch(JMSException e){
			throw new RuntimeException(e);
		}
		return msgContent;
	}
	
	public static void logHeaders(Message message, Class senderClass) {
		
		try{
			StringBuffer headersAsSb= new StringBuffer();
			headersAsSb.append("\nMessageId: "+message.getJMSMessageID());
			headersAsSb.append("\nCorrelationId: "+message.getJMSMessageID());
			headersAsSb.append("\nRedelivered: "+message.getJMSRedelivered());
			headersAsSb.append("\nPriority: "+message.getJMSPriority());
			headersAsSb.append("\nDeliveryMode: "+message.getJMSDeliveryMode());
			
			log.info("[ {} ] \nHeaders: {} ",senderClass.getName(),headersAsSb.toString());
			
		}catch (JMSException e){
			throw new RuntimeException(e);
		}
	
	}
	
	public static void testTransazioneInRollback (Session currentSession) throws JMSException{
		
		currentSession.rollback();
		//throw new RuntimeException("Test Rollback in producer");
	}
}
