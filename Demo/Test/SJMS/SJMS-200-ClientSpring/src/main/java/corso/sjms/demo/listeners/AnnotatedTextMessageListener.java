package corso.sjms.demo.listeners;


import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;

import lombok.extern.slf4j.Slf4j;

public class AnnotatedTextMessageListener extends AbstractMessageProcessor {
    	
	@JmsListener(destination = "see.jms.test.queue")
	public void processMessage(Message message){
		processAndLogMessage(message);
	}
    
    
}




