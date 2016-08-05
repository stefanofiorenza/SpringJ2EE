package corso.spring.messaging.demo.jms.transazioni.consumers;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import corso.spring.messaging.demo.jms.common.Constants;
import corso.spring.messaging.demo.jms.common.MessageStoreUtil;

public class SpringMessageListener implements MessageListener,SpringMessageConsumer{

	@Override
	public void onMessage(Message message) {

		
		try {
			ObjectMessage messaggio = (ObjectMessage)message;
			int indice=messaggio.getIntProperty("ordinale");
			System.out.println("[SpringMessageListener]:ricevuto messaggio "+indice);
			String fileName=messaggio.getStringProperty("categoria")+indice;
			
			//simulazione di elaborazione complessa..
			Random rnd = new Random();
			int tempoElaborazione=rnd.nextInt(3000);
			Thread.sleep((long)tempoElaborazione);
			
			MessageStoreUtil.saveMessage(Constants.CONSUMER_PATH, fileName, messaggio);
			System.out.println("[SpringMessageListener]:messaggio "+indice+" salvato");
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
