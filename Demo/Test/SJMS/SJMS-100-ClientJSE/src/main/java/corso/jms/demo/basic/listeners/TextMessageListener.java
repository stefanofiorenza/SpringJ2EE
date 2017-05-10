package corso.jms.demo.basic.listeners;


import javax.jms.*;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.common.JmsDemoUtils;

@Slf4j
public class TextMessageListener implements MessageListener {
    
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private int ackMode;
	
	public TextMessageListener(){}
	
	public TextMessageListener(String nome,int ackMode){
		this.nome = nome;
		this.ackMode=ackMode;
	}
   
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            
            JmsDemoUtils.logHeaders(message, this.getClass());
                                               
            JmsDemoUtils.validateMessage(message);
            
           
            
//            if(this.ackMode== Session.CLIENT_ACKNOWLEDGE){
//            	message.acknowledge();
//            }
    		
            log.info("[LISTENER]["+this.getNome()+"]: Ricevuto Messaggio:" + msg.getText() );
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}




