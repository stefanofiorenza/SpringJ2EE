package corso.jms.demo.basic.consumer.listener;


import javax.jms.*;

import lombok.extern.slf4j.Slf4j;
import corso.jms.demo.basic.common.ConsumerUtils;

@Slf4j
public class TextMessageListener implements MessageListener {
    
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TextMessageListener(String nome){
		this.nome = nome;
	}
   
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            
            ConsumerUtils.validateMessage(message);
    		
            log.info("[LISTENER]["+this.getNome()+"]: Ricevuto Messaggio:" + msg.getText() );
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}




