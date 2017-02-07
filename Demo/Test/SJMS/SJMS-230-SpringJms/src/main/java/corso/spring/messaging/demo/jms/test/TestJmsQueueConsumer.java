package corso.spring.messaging.demo.jms.test;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.spring.jms.demo.consumers.SpringQueueConsumer02PollingGateway;


@Slf4j
public class TestJmsQueueConsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//testPolling();
		testEventDriven();
//		testPollingDLQ();
	}
	

	private static void testPolling(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/jms/demo/config/jms-01PollingDemo-config.xml");
		//SpringQueueConsumer01Polling consumer = (SpringQueueConsumer01Polling) context.getBean("queueConsumer01Polling");
		SpringQueueConsumer02PollingGateway consumer = (SpringQueueConsumer02PollingGateway) context.getBean("queueConsumer02Gateway");
		consumer.receiveAllTextMessage();
//		activatePollingDLQ(context);
	
	}
	
	private static void testEventDriven(){
		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/jms/demo/config/jms-02EventDriven-config.xml");
		log.info("Consumer Event Driven in ascolto..");
//		activatePollingDLQ(context);		
	}
	
//	private static void testPollingDLQ() {
//		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/jms/demo/config/jms-00ContextConfig.xml");
//		activatePollingDLQ(context);		
//	}
//	
//	private static void activatePollingDLQ(ApplicationContext context){
//		
//		SpringQueueConsumer01Polling consumerDlq = (SpringQueueConsumer01Polling) context.getBean("queueConsumerPollingDLQ");
//		consumerDlq.receiveAllTextMessage();
//		log.info("Poller su DLQ Attivato..");
//	
//	}

}
