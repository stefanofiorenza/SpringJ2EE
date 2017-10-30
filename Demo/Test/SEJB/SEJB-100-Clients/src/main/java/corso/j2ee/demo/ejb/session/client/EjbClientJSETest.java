package corso.j2ee.demo.ejb.session.client;

import java.util.Properties;
 






import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



import lombok.extern.slf4j.Slf4j;
import corso.ejb.demo.servizi.basic.io.HelloService;
import corso.ejb.demo.servizi.basic.io.UserService;
import corso.ejb.demo.servizi.basic.io.UserServiceRemote;
import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;
import corso.ejb.demo.servizi.basic.io.model.User;



@Slf4j
public class EjbClientJSETest {

	
	public static void main(String[] args) {

	try {
			
			log.info("Recupero oggetto registrato sul servizio jndi di Jboss...");
			Context initCtx = new InitialContext();		
			//testHelloService(initCtx);
			testUserService(initCtx);
		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
	
	
	private static void testHelloService(Context initCtx) throws NamingException{
		Object service = initCtx.lookup(JBossUtils.JBOSS_71_HELLOSERVICE);
		log.info("Oggetto recuperato:\n");
		HelloService helloService=(HelloService)service;			
		log.info(helloService.hello("Stefano"));
	}
	
	private static void testUserService(Context initCtx) throws NamingException, ServiceException{
		
		
		
		UserService servizio = (UserService)initCtx.lookup(JBossUtils.EAR_USERSERVICE_SL_JBOSS71);
		
		User user= new User();
		user.setNome("Stefano");
		user.setCognome("Fiorenza");
		user.setEmail("stefano@fiorenza.it");
		user.setTelefono("0645849583040");
		
		User utente;
		utente = servizio.echoUser(user);
					
		System.out.println("Risultato chiamata remota:\n");
		System.out.println(utente.toString());
	}
	
	
	public static Context getInitialContextForJBossFromProps() throws NamingException{
		return new InitialContext();
	}
	
	
	public static Context getInitialContextForJBossFromCode() throws NamingException{
		
		Properties properties = new Properties();
		// inserisco il nome del driver per la connessione al server jndi da properties
		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		//inserisco l'url del server jndi
		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		
		Context jndiContext = new InitialContext(properties);      

		return jndiContext;
	}


}
