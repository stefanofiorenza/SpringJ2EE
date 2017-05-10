package corso.sjms.demo.config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import corso.sjms.demo.listeners.TextMessageListener;

@Configuration
@Import(Sjms200CommonConfig.class)
public class Sjms200EventDrivenConfig01 {

	@Autowired
	private Sjms200CommonConfig commonConfig;
	
	
	@Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer() {
		DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
		dmlc.setMessageListener(new TextMessageListener("EDCConfigClass",1));
		dmlc.setConnectionFactory((ConnectionFactory)commonConfig.connectionFactory().getObject());
		dmlc.setDestination((Destination)commonConfig.jmsDestination().getObject());		
		return dmlc;
    }

	
}
