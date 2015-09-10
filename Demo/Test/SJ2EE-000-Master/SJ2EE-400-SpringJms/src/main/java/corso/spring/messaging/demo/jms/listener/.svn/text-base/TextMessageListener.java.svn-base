package corso.spring.messaging.demo.jms.listener;


import javax.jms.*;


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
            System.out.println("[LISTENER]["+this.getNome()+"]: Ricevuto Messaggio:" + msg.getText() );
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}




