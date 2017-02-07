package corso.spring.messaging.demo.jms.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JmsConsumerHelper {

	
	public static void pollingForAllMessages(JmsTemplate jmsTemplate,String exitMessage) throws JMSException{
		pollingForAllMessages(jmsTemplate,null,exitMessage,0);
	}
		
	public static void pollingForAllMessages(JmsTemplate jmsTemplate,Session session, String exitMessage, int commitInterval) throws JMSException{

		log.info("Star polling for messages..");			
		String msgContent="";
		
		int consumed=0;
		int acked=0;
		
		while(!msgContent.equals(exitMessage)){	
			
			if(commitInterval>0 && consumed!=0 && consumed%commitInterval==0){				
				msgContent=pollingForOneTextMessage(jmsTemplate);
				session.commit();
				acked++;
			}else{
				acked++; //autoack
			}
			consumed++;			
		}				
		log.info("Polling ended for message: {} Consumed: {} Acked: {} ",msgContent,consumed,acked);
	}
	
	
	public static String pollingForOneTextMessage(JmsTemplate jmsTemplate) throws JMSException{
		Message message = jmsTemplate.receive(); //istruzione bloccante fino a che non ce n'e' un altro msg
		log.info("A message exists...");				
		return processMessage(message);		
	}
	
	
	
	public static String processMessage(Message message) throws JMSException{
		String msgContent="";
		
		validateMessage(message);
		
		TextMessage textMessage = (TextMessage) message;
		msgContent=textMessage.getText();
		
		log.info("Received: "+msgContent);		
		return msgContent;
	}
	
	
	
	private static void validateMessage(Message message) throws JMSException{
		
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
