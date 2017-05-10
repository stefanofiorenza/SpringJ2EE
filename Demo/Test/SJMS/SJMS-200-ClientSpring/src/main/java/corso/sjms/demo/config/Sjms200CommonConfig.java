package corso.sjms.demo.config;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import corso.sjms.demo.clients.JmsConsumerSpringImpl;
import corso.sjms.demo.clients.JmsProducerSpringImpl;

@Configuration
public class Sjms200CommonConfig {

	private Properties jndiJmsEnvProps(){
		Properties env = new Properties();
		env.put("java.naming.factory.initial",
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.put("java.naming.provider.url","tcp://localhost:61616");		
		env.put("connectionFactoryNames","connectionFactory");
		env.put("queue.testQueue","see.jms.test.queue");
		env.put("topic.testTopic","see.jms.test.topic");
		env.put("queue.dlq","see.jms.test.dlq");
		return env;
	}
	
	@Bean
	public JndiTemplate jndiTemplate(){		
		JndiTemplate jndiTemplate= new JndiTemplate();
		jndiTemplate.setEnvironment(jndiJmsEnvProps());
		return jndiTemplate;
	}
	
	
	@Bean
	public JndiObjectFactoryBean connectionFactory(){
		JndiObjectFactoryBean connFactoryAsJndi=new JndiObjectFactoryBean();
		connFactoryAsJndi.setJndiName("connectionFactory");
		connFactoryAsJndi.setJndiTemplate(jndiTemplate());
		return connFactoryAsJndi;
	}
	
	@Bean
	public JndiObjectFactoryBean jmsDestination(){
		JndiObjectFactoryBean destinationAsJndi=new JndiObjectFactoryBean();
		destinationAsJndi.setJndiName("testQueue");
		destinationAsJndi.setJndiTemplate(jndiTemplate());
		return destinationAsJndi;
	}
	
	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate= new JmsTemplate();
		jmsTemplate.setConnectionFactory((ConnectionFactory)connectionFactory().getObject());
		jmsTemplate.setDefaultDestination((Destination)jmsDestination().getObject());
		return jmsTemplate;
	}
	
	@Bean
	public JmsProducerSpringImpl queueProducer(){
		JmsProducerSpringImpl producer = new JmsProducerSpringImpl();
		producer.setName("Producer");
		producer.setAckMode(1);
		producer.setTransacted(true);
		producer.setDestination((Destination)jmsDestination().getObject());
		producer.setJmsTemplate(jmsTemplate());		
		return producer;
	}
	

	
}
