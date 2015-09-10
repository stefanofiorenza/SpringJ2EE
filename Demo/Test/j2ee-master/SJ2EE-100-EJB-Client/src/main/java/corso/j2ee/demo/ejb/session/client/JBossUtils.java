package corso.j2ee.demo.ejb.session.client;

public class JBossUtils {

	//STATELESS:
	
	
	//STATEFUL
//	Bean bean = (Bean) context.lookup("ejb:" + appName + "/"
//			+ moduleName + "/" + distinctName + "/" + beanName + "!"
//			+ viewClassName + "?stateful");
	
	/**
	 * Cio che segue java:jboss/exported/ da logs di JBoss
	 */
	public static final String EAR_USERSERVICE_SL_JBOSS71="ejb:EJB-102-APP/EJB-101-Sessions/UserServiceImpl!corso.ejb.demo.servizi.basic.io.UserServiceRemote";
	public static final String EAR_USERSERVICE_SF_JBOSS71="ejb:EJB-102-APP/EJB-101-Sessions/UserServiceImpl!corso.ejb.demo.servizi.basic.io.UserServiceRemote?stateful";

	public static final String JBOSS_71_HELLOSERVICE="ejb:EJB-102-APP/EJB-101-Sessions/HelloServiceImpl!corso.ejb.demo.servizi.basic.io.HelloServiceRemote";
	public static final String EJBSESSION_JBOSS71_STATEFUL="ejb:JEE-100-EJB-Session/UserServiceImpl!corso.ejb.demo.servizi.basic.io.UserServiceRemote?stateful";
	public static final String EJBSESSION_JBOSS51="UserServiceImpl/remote";
}
