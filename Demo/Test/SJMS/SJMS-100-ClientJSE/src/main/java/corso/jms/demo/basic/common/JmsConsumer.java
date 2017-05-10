package corso.jms.demo.basic.common;


import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.config.Configs;
import corso.jms.demo.basic.config.DestinationType;
import corso.jms.demo.basic.listeners.TextMessageListener;


@Slf4j
public class JmsConsumer extends JmsClient{

	private MessageConsumer consumer;

		
	@Getter
	@Setter
	private String name;
	
	public JmsConsumer(String name, ConnectionFactory connectionFactory, Destination destination,String destinationType, 
			String user, String pw,  boolean sessionTransactional, int ackMode){
		
		super(connectionFactory, destination,destinationType, user, pw, sessionTransactional,  ackMode);
		this.setName(name);
		createConsumerInternal();
	}

	
	private void createConsumerInternal(){
		try{		
			
			switch (destinationType) {
			case Configs.QUEUE_TYPE:
				consumer = ((QueueSession) session).createReceiver((Queue) destination);
				break;
				
			case Configs.TOPIC_TYPE:
				consumer = ((TopicSession) session).createSubscriber((Topic) destination);
				break;
			}				
		}		
		catch (JMSException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	public void setTextMessageListener(TextMessageListener listener){	
		try {
			
			listener.setNome(this.name);
			listener.setAckMode(this.ackMode);			
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
						
			if(this.transacted && commitInterval>0 && 
					consumed!=0 && consumed%commitInterval==0){
				//ConsumerUtils.testTransazioneInRollback(session);
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
		
		JmsDemoUtils.validateMessage(message);
		JmsDemoUtils.logHeaders(message, this.getClass());
		
		
		//se non e' transazionale e ack manuale si da l'ack ad ogni messaggio processato correttamente
		if(this.ackMode== Session.CLIENT_ACKNOWLEDGE && !this.transacted){ 
			message.acknowledge();
		}
		
		TextMessage textMessage = (TextMessage) message;
		msgContent=textMessage.getText();
		
		log.info("[ {} ]: Received: {} ",this.getName(),msgContent);		
		return msgContent;
	}
	
	
	
	


}
