package corso.sjms.demo.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.sjms.demo.clients.JmsConsumerSpringImpl;
import corso.sjms.demo.clients.JmsProducerSpringImpl;
import corso.sjms.demo.common.JmsDemoUtils;
import corso.sjms.demo.common.XmlConfigs;
import corso.sjms.demo.config.Sjms200EventDrivenConfig01;
import corso.sjms.demo.config.Sjms200EventDrivenConfig02;
import corso.sjms.demo.config.Sjms200PollingConfig;

@Slf4j
public class TestSJmsConfigurations {

	
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	
	public static void main(String[] args) {
		
		
		//*******  POLLING DEMO ****************/
		//TC1) testPollingFromQueueXmlConfig();
		
		//TC2) testPollingFromTopicXmlConfig();
		
		//TC3) testPollingFromConfigClass();
		
		
		//**** EVENT DRIVEN ****************/
		//TC4) testEventDrivenFromXml();
		
		//TC5) 
		testEventDrivenTopicFromXml();
		
		//TC6) testEventDrivenFromXmlAdapter();
		
		//TC7) testEventDrivenFromXmlNs();
		
		//TC8) testEventDrivenFromXmlNs_Adapters();
		
		//TC9) testEventDrivenFromConfigClass();
		
		//TC10) testEventDrivenFromConfigClassJmsAnnotations();
	}
	
	



	private static final String messageToSend="OneMessage";
	
	
	
	private static void testPollingFromQueueXmlConfig(){		
		testTemplatePolling(XmlConfigs.PRODUCER_QUEUE,XmlConfigs.CONSUMER_POLLING_QUEUE,
				new ClassPathXmlApplicationContext(XmlConfigs.POLLING_QUEUE));	
	}
	
	private static void testPollingFromTopicXmlConfig(){		
		testTemplatePolling(XmlConfigs.PRODUCER_TOPIC,XmlConfigs.CONSUMER_POLLING_TOPIC,
				new ClassPathXmlApplicationContext(XmlConfigs.POLLING_TOPIC));	
	}
	
	
	
	
	private static void testPollingFromConfigClass(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Sjms200PollingConfig.class);
		ctx.refresh();
		testTemplatePolling(XmlConfigs.PRODUCER_QUEUE, XmlConfigs.CONSUMER_POLLING_QUEUE,ctx);		
	}
	
	private static void testEventDrivenFromXml(){
		testTemplateEventDriven(new ClassPathXmlApplicationContext(XmlConfigs.EVENT_DRIVEN_01_BEANS));		
	}
	
	private static void testEventDrivenTopicFromXml() {
		testTemplateEventDriven(XmlConfigs.PRODUCER_TOPIC,new ClassPathXmlApplicationContext(XmlConfigs.EVENT_DRIVEN_TOPIC));		
	}
	
	private static void testEventDrivenFromXmlAdapter(){
		testTemplateEventDriven(new ClassPathXmlApplicationContext(XmlConfigs.EVENT_DRIVEN_02_ADAPTER));	
	}
	
	private static void testEventDrivenFromXmlNs(){
		testTemplateEventDriven(new ClassPathXmlApplicationContext(XmlConfigs.EVENT_DRIVEN_03_NS));
	}
	
	private static void testEventDrivenFromXmlNs_Adapters(){
		testTemplateEventDriven(new ClassPathXmlApplicationContext(XmlConfigs.EVENT_DRIVEN_04_NS_ADAPTER));
	}
	
	private static void testEventDrivenFromConfigClass(){		
		testTemplateEventDriven(Sjms200EventDrivenConfig01.class);	
	}
	
	private static void testEventDrivenFromConfigClassJmsAnnotations(){		
		testTemplateEventDriven(Sjms200EventDrivenConfig02.class);	
	}
	
	
	
	private static void testTemplateEventDriven(Class configClass) {	
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(configClass);
		ctx.refresh();
		testTemplateEventDriven(ctx);
		
	}

	private static void testTemplateEventDriven(String producerName,ApplicationContext ctx) {
		JmsProducerSpringImpl producer=  ctx.getBean(producerName,JmsProducerSpringImpl.class);
		producer.sendManyTextMessage(15, 0);
		
	}
	
	private static void testTemplateEventDriven(ApplicationContext ctx) {
		testTemplateEventDriven(XmlConfigs.PRODUCER_QUEUE,ctx);		
	}
	
	
	private static void testTemplatePolling(String producerName,String consumerName,ApplicationContext context ){		
		
		JmsConsumerSpringImpl consumer = context.getBean(consumerName,JmsConsumerSpringImpl.class);
		JmsProducerSpringImpl producer=  context.getBean(producerName,JmsProducerSpringImpl.class);
		
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				try{
					consumer.pollingForAllMessages(JmsDemoUtils.MESSAGE_EXIT);
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}							
			}					
		});				
		
		executor.submit(new Runnable() {			
			@Override
			public void run() {
				try{
					producer.sendManyTextMessage(30, 0);
				}catch (Exception e){
					log.error(e.getMessage(),e);
				}
							
			}					
		});				
		
			
		
		
			
	}

}
