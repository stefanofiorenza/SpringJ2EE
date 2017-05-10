package corso.sjms.demo.listeners;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import corso.sjms.demo.common.JmsDemoUtils;

@Slf4j
public abstract class AbstractMessageProcessor {
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private int ackMode;
	
    
    protected void processAndLogMessage(Message message){
    	   try {
               TextMessage msg = (TextMessage) message;
               
               JmsDemoUtils.logHeaders(message, this.getClass());
                                                  
               JmsDemoUtils.validateMessage(message);
                 
//               if(this.ackMode== Session.CLIENT_ACKNOWLEDGE){
//               	message.acknowledge();
//               }
       		
               log.info("[LISTENER]["+this.getNome()+"]: Ricevuto Messaggio:" + msg.getText() );
           } catch (JMSException ex) {
               ex.printStackTrace();
           }
      }
}
