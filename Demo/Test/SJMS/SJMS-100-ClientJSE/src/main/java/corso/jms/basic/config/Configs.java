package corso.jms.basic.config;


public class Configs {

	public static  final String JBOSS_71_QUEUE_USER="";
	public static  final String JBOSS_71_QUEUE_PW="";
	public static  final String JBOSS_71_TOPIC_USER="";
	public static  final String JBOSS_71_TOPIC_PW="";
	
	
	public static final String ACTIVEMQ_HOST="localhost";
	public static final int ACTIVEMQ_PORT=61616;
	public static final String ACTIVEMQ_USER="admin";
	public static final String ACTIVEMQ_PW="admin";
	public static final String ACTIVEMQ_LOCATION_URI = "tcp://" + ACTIVEMQ_HOST + ":" + ACTIVEMQ_PORT;
	public static final String ACTIVEMQ_QUEUE_NAME = "TestQueue";
	public static final String ACTIVEMQ_TOPIC_NAME = "TestTopic";

	
	public static final boolean SESSION_TRANSACTIONAL=true;
	public static final boolean SESSION_NON_TRANSACTIONAL=false;
	
	public static final String MESSAGE_EXIT="CLOSE";
	
	public static final String USERNAME = "broker.connection.username";
	public static final String PASSWORD = "broker.connection.password";	
	public static final String CONNECTION_FACTORY_CFG_KEY="connectionFactoryNames";
	public static final String QUEUE_CFG_KEY="jndi.registry.queuename";
	public static final String TOPIC_CFG_KEY="jndi.registry.topicname";
	public static final String TRANSACTIONAL_KEY = "jms.session.transactional";
	public static final String ACK_MODE_KEY = "jms.session.ackMode";
	public static final String DEST_TYPE_MODE_KEY = "jms.destination.type";
	
	public static final String  QUEUE_TYPE="queue";
	public static final String  TOPIC_TYPE="topic";
	
	
	
}
