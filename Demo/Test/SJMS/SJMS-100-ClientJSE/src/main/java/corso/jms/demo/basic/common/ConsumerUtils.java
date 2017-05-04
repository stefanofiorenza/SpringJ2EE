package corso.jms.demo.basic.common;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class ConsumerUtils {

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
}
