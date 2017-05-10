package corso.sjms.demo.listeners;


import javax.jms.Message;
import javax.jms.MessageListener;

public class TextMessageListener extends  AbstractMessageProcessor implements MessageListener{
    
	
	public TextMessageListener(){}
	
	public TextMessageListener(String name, int ackMode) {
		setNome(name);
		setAckMode(ackMode);		
	}

	@Override
    public void onMessage(Message message) {
    	processAndLogMessage(message);
    }
    
    
    
}




