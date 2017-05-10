package corso.sjms.demo.config;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import corso.sjms.demo.clients.JmsConsumerSpringImpl;
import corso.sjms.demo.clients.JmsProducerSpringImpl;

@Configuration
@Import(Sjms200CommonConfig.class)
public class Sjms200PollingConfig {

	@Autowired
	private Sjms200CommonConfig commonConfig;
	
	@Bean
	public JmsConsumerSpringImpl queueConsumer(){
		JmsConsumerSpringImpl consumer = new JmsConsumerSpringImpl();
		consumer.setName("Consumer");
		consumer.setAckMode(1);
		consumer.setTransacted(true);
		consumer.setDestination((Destination)commonConfig.jmsDestination().getObject());
		consumer.setJmsTemplate(commonConfig.jmsTemplate());		
		return consumer;
	}
	
}
