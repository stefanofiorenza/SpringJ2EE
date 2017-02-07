package corso.jms.basic.config;

import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import corso.jms.basic.common.JmsConsumer;
import corso.jms.basic.common.JmsProducer;

public class ActiveMqUtils {

	private static ActiveMQConnectionFactory  connFactory=new ActiveMQConnectionFactory (Configs.ACTIVEMQ_LOCATION_URI);
	
	public static JmsConsumer createQueueConsumerActiveMq(){
		return createConsumerActiveMq(Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE,DestinationType.QUEUE);
	}
	
	public static JmsConsumer createTopicConsumerActiveMq(){
		return createConsumerActiveMq(Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE,DestinationType.TOPIC);
	}
	
	public static JmsConsumer createQueueConsumerActiveMq( boolean transactional, int ackType){
		return createConsumerActiveMq(transactional, ackType,DestinationType.QUEUE);
	}
	
	public static JmsConsumer createTopicConsumerActiveMq( boolean transactional, int ackType){
		return createConsumerActiveMq(transactional, ackType,DestinationType.TOPIC);
	}
	
		
	public static JmsProducer createQueueProducerActiveMq(){
		return createProducerActiveMq(Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE,DestinationType.QUEUE);
	}
	
	public static JmsProducer createTopicProducerActiveMq(){
		return createProducerActiveMq(Configs.SESSION_NON_TRANSACTIONAL, Session.AUTO_ACKNOWLEDGE,DestinationType.TOPIC);
	}
	
	
	public static JmsProducer createQueueProducerActiveMq( boolean transactional, int ackType){
		return createProducerActiveMq(transactional, ackType,DestinationType.QUEUE);
	}
	
	public static JmsProducer createTopicProducerActiveMq( boolean transactional, int ackType){
		return createProducerActiveMq(transactional, ackType,DestinationType.TOPIC);
	}
	
	
	
	private static JmsConsumer createConsumerActiveMq( boolean transactional, int ackType,DestinationType destType){
		
		return new JmsConsumer(connFactory, Configs.ACTIVEMQ_QUEUE_NAME, 
				Configs.ACTIVEMQ_USER, Configs.ACTIVEMQ_PW, transactional, ackType, destType);
	} 
	
	
	private static JmsProducer createProducerActiveMq( boolean transactional, int ackType,DestinationType destType){
			
		return new JmsProducer(connFactory, Configs.ACTIVEMQ_QUEUE_NAME, 
				Configs.ACTIVEMQ_USER, Configs.ACTIVEMQ_PW, transactional, ackType, destType);
	} 
	
	
}
