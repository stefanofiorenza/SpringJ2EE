package corso.sjms.demo.adapters;

import javax.jms.Message;

import corso.sjms.demo.listeners.AbstractMessageProcessor;

public class JmsMessageAdapterDelegate  extends AbstractMessageProcessor{

	public void receiveMessage(Message message){
		processAndLogMessage(message);
	}
}
