package corso.sjms.demo.clients;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.core.MessageCreator;

import corso.sjms.demo.common.JmsClientTemplateImpl;


@Slf4j
public class JmsProducerSpringImpl extends JmsClientTemplateImpl{
			
	
	public void sendManyTextMessage(int howMany, int howOftenIllegalMsg, int commitInterval) {
	
		int sent = 0;

		for (int i = 0; i < howMany; i++) {

			if (howOftenIllegalMsg > 0 && i % howOftenIllegalMsg == 0) {
				sendTextMessage("");
			} else {
				sendTextMessage("Message#" + i);
			}
			
			sent++;

			if (this.isTransacted() && commitInterval > 0
					&& sent % commitInterval == 0) {
				// JmsDemoUtils.testTransazioneInRollback(session);
				commitJmsSession();
			}
		}		
	}
	
	public void sendManyTextMessage(int howMany, int howOftenIllegalMsg){		
		sendManyTextMessage(howMany,  howOftenIllegalMsg,0);
	}
	
	public void sendTextMessage(String msgContent){
	
		getJmsTemplate().send(getDestination(), new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(msgContent);
				log.info("Message Created with content: [ {} ] ...", message);
				return message;
			}
		});

		log.info("Message with content: [ {} ] Sent to Destination: {} ", msgContent, this.getDestination());
					   
	}
		

	/**
	 * Da modificare per provare JmsTemplate api
	 */
	public void jmsProducerApiDemo(){
		//getJmsTemplate().
	}
}
