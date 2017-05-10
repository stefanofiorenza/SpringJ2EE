package corso.sjms.demo.clients;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;
import corso.sjms.demo.common.JmsClientGatewayImpl;
import corso.sjms.demo.common.JmsDemoUtils;


@Slf4j
public class JmsConsumerSpringImpl 
	extends JmsClientGatewayImpl{
//extends JmsClientTemplateImpl{
		
		
	
	public void pollingForAllMessages(String exitMessage) throws JMSException{
		pollingForAllMessages(exitMessage,0);
	}
	
	
	public void pollingForAllMessages(String exitMessage, int commitInterval){

		log.info("Start polling for messages..");			
		String msgContent="";
		
		int consumed=0;
				
		while(!msgContent.equals(exitMessage)){	
			
			msgContent=pollingForOneTextMessage();
						
			if(this.isTransacted() && commitInterval>0 && 
					consumed!=0 && consumed%commitInterval==0){
				//ConsumerUtils.testTransazioneInRollback(session);
				commitJmsSession();						
			}
			consumed++;			
		}				
		log.info("{}: Polling ended for message: {} Consumed: {} ",this.getName(),msgContent,consumed);
	}
	
	
	public String pollingForOneTextMessage() {
		
		//istruzione bloccante fino a che non ce n'e' un altro msg 		
		Message message = getJmsTemplate().receive(getDestination());
		
		//dopo timeout restituisce null
		//Message message = consumer.receive(Configs.RECEIVE_TIMEOUT_LIMIT); 
		
		log.info("[ {} ]: A message exists... in Destination: {} ",this.getName(),this.getDestination());				
		return processMessage(message);		
	}
	
	
	private String processMessage(Message message) {
		String msgContent="";
		
		JmsDemoUtils.validateMessage(message);
		JmsDemoUtils.logHeaders(message, this.getClass());
		
		
		//se non e' transazionale e ack manuale si da l'ack ad ogni messaggio processato correttamente
		if(this.getAckMode()== Session.CLIENT_ACKNOWLEDGE && !this.isTransacted()){ 
			JmsDemoUtils.ackMessage(message);
		}
				
		msgContent=JmsDemoUtils.extractTextPayload((TextMessage) message);
		
		log.info("[ {} ]: Received: {} ",this.getName(),msgContent);		
		return msgContent;
	}
	
	
	public void jmsConsumerApiDemo(){
		//getJmsTemplate().
	}
	


}
