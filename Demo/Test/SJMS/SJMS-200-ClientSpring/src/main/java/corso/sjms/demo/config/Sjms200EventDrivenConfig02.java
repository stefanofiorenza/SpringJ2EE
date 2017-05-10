package corso.sjms.demo.config;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import corso.sjms.demo.clients.JmsConsumerSpringImpl;
import corso.sjms.demo.clients.JmsProducerSpringImpl;
import corso.sjms.demo.listeners.AnnotatedTextMessageListener;

@Configuration
@EnableJms //abilita la scansione dell'annotation @JmsListener 
@Import(Sjms200CommonConfig.class)
public class Sjms200EventDrivenConfig02 {

	@Autowired
	private Sjms200CommonConfig commonConfig;
	
	@Bean //Scansione jms annotations tra bean gia nel contesto
    public AnnotatedTextMessageListener listener(){
		AnnotatedTextMessageListener listener = new AnnotatedTextMessageListener();
		listener.setAckMode(1);
		listener.setNome("EDCJmsAnnotatedClass");
		return listener;
	}
	
	@Bean //necessario avere nel contesto la factory di DMLC
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory= new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory((ConnectionFactory)commonConfig.connectionFactory().getObject());
        return factory;
    }

	
}
