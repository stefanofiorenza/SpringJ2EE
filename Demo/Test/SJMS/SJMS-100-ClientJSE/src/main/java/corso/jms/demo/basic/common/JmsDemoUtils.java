package corso.jms.demo.basic.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JmsDemoUtils {

	
	public static void validateMessage(Message message) throws JMSException{
		
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
	
	public static void logHeaders(Message message, Class senderClass) throws JMSException{
		
		StringBuffer headersAsSb= new StringBuffer();
		headersAsSb.append("\nMessageId: "+message.getJMSMessageID());
		headersAsSb.append("\nCorrelationId: "+message.getJMSMessageID());
		headersAsSb.append("\nRedelivered: "+message.getJMSRedelivered());
		headersAsSb.append("\nPriority: "+message.getJMSPriority());
		headersAsSb.append("\nDeliveryMode: "+message.getJMSDeliveryMode());
		
		log.info("[ {} ] \nHeaders: {} ",senderClass.getName(),headersAsSb.toString());
	}
	
	public static void testTransazioneInRollback (Session currentSession) throws JMSException{
		
		currentSession.rollback();
		//throw new RuntimeException("Test Rollback in producer");
	}
}
