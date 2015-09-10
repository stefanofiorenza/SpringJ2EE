package corso.spring.messaging.demo.jms.queue.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.messaging.demo.jms.queue.SpringQueueConsumer;

public class TestJmsQueueConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =new ClassPathXmlApplicationContext("context-config-jboss71.xml");
		SpringQueueConsumer consumer = (SpringQueueConsumer) context.getBean("queueConsumer");
		
		//for (int i=0; i<30; i++){
			consumer.receiveTextMessage();
		//}
	}

}
