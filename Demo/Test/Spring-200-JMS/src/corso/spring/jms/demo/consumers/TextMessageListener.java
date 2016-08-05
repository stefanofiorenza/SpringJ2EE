package corso.spring.jms.demo.consumers;


import javax.jms.*;


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
       	  	System.out.println("[LISTENER]["+this.getNome()+"]["+Thread.currentThread().getName()+"]: Ricevuto Messaggio:" + msg.getText() );
          
            if(msg.getText().contains("4")){
            	System.out.println("[LISTENER]["+this.getNome()+"]:ricevuto "+msg.getText()+" Eccezione!!!");
            	System.out.println("[LISTENER]["+this.getNome()+"]: Non processato per valore: "+4+" Verranno eseguiti altri tentativi prima della DLQS");
            	throw new RuntimeException("Valore=4 Exception");
            	
            }else{
            	  System.out.println("[LISTENER]["+this.getNome()+"]: Processato Con Successo Messaggio:" + msg.getText() );
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}




