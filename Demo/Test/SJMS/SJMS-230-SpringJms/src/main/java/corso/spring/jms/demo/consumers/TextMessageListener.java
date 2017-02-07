package corso.spring.jms.demo.consumers;


import javax.jms.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextMessageListener implements MessageListener {
    
	private String nome;

	public String getNome() {
		return nome; 
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	   
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
       	  	log.info("[LISTENER]["+this.getNome()+"]["+Thread.currentThread().getName()+"]: Ricevuto Messaggio:" + msg.getText() );
          	JmsConsumerHelper.processMessage(message);
          	log.info("[LISTENER]["+this.getNome()+"]: Processato Con Successo Messaggio:" + msg.getText() );

        } catch (JMSException ex) {
            ex.printStackTrace();
        }catch (RuntimeException ex) {
        	log.error(ex.getMessage(),ex); //log ex per renderla visibile
        	throw ex;
        }
    }
}




