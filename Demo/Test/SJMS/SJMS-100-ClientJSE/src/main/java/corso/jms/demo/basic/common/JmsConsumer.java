package corso.jms.demo.basic.common;


import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.config.Configs;
import corso.jms.demo.basic.consumer.listener.TextMessageListener;


@Slf4j
public class JmsConsumer extends JmsClient{

	private MessageConsumer consumer;

		
	@Getter
	@Setter
	private String name;
	
	public JmsConsumer(String name, ConnectionFactory connectionFactory, Destination destination, String user, String pw, 
		 boolean sessionTransactional, int ackMode){
		
		super(connectionFactory,  destination,user, pw, sessionTransactional,  ackMode);
		this.setName(name);
		createConsumerInternal();
	}
	
//	public JmsConsumer(ConnectionFactory connectionFactory, String destinationName, String user, String pw, 
//			 boolean sessionTransactional, int ackMode, DestinationType destType){
//		
//			super(connectionFactory, destinationName,user, pw, sessionTransactional,  ackMode,destType);
//			createConsumerInternal();
//	}
	
	
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

		log.info("Start polling for messages..");			
		String msgContent="";
		
		int consumed=0;
				
		while(!msgContent.equals(exitMessage)){	
			
			msgContent=pollingForOneTextMessage();
						
			if(session.getTransacted() && commitInterval>0 && 
					consumed!=0 && consumed%commitInterval==0){
				session.commit();				
			}
			consumed++;			
		}				
		log.info("{}: Polling ended for message: {} Consumed: {} ",this.getName(),msgContent,consumed);
	}
	
	
	public String pollingForOneTextMessage() throws JMSException{
		
		//istruzione bloccante fino a che non ce n'e' un altro msg 		
		Message message = consumer.receive(); 
		
		//dopo timeout restituisce null
		//Message message = consumer.receive(Configs.RECEIVE_TIMEOUT_LIMIT); 
		
		log.info("[ {} ]: A message exists...",this.getName());				
		return processMessage(message);		
	}
	
	
	private String processMessage(Message message) throws JMSException{
		String msgContent="";
		
		//ConsumerUtils.validateMessage(message);
		
		if(this.ackMode== Session.CLIENT_ACKNOWLEDGE){
			message.acknowledge();
		}
		
		TextMessage textMessage = (TextMessage) message;
		msgContent=textMessage.getText();
		
		log.info("[ {} ]: Received: {} ",this.getName(),msgContent);		
		return msgContent;
	}
	
	
	
	


}
