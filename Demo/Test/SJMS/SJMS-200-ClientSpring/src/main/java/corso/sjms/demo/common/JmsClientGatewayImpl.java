package corso.sjms.demo.common;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.core.support.JmsGatewaySupport;

import lombok.Getter;
import lombok.Setter;


public class JmsClientGatewayImpl  extends JmsGatewaySupport{

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private Destination destination;
	
	@Getter
	@Setter
	private int ackMode;
	
	@Getter
	@Setter
	private boolean transacted;

	
	
	protected void commitJmsSession(){		
		getJmsTemplate().execute(sessionCommitStrategy);
	}
	
	protected void rollbackJmsSession(){		
		getJmsTemplate().execute(sessionRollbackStrategy);
	}
	
	
	private final SessionCallback sessionCommitStrategy=new SessionCallback() {
		public Object doInJms(Session session) throws JMSException{
			session.commit();
			return null;
		}
	};
	
	
	private final SessionCallback sessionRollbackStrategy=new SessionCallback() {
		public Object doInJms(Session session) throws JMSException{
			session.rollback();
			return null;
		}
	};
	
}
