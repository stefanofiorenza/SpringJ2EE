package corso.j2ee.demo.ejb.session.client;

import java.util.Properties;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.ejb.demo.servizi.basic.io.UserService;
import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;
import corso.ejb.demo.servizi.basic.io.model.User;




public class EjbClientJSETest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	try {
			
			
			System.out.println("Recupero oggetto registrato sul servizio jndi di Jboss...");
			Context initCtx = getInitialContextForJBoss();
			UserService servizio = (UserService)initCtx.lookup("UserServiceImpl/remote");
		
			User user= new User();
			user.setNome("Stefano");
			user.setCognome("Fiorenza");
			user.setEmail("stefano@fiorenza.it");
			user.setTelefono("0645849583040");
			
			User utente;
			utente = servizio.echoUser(user);
						
			System.out.println("Risultato chiamata remota:\n");
			System.out.println(utente.toString());
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Context getInitialContextForJBoss() throws NamingException{
		Context jndiContext = new InitialContext();
		Properties properties = new Properties();
		// inserisco il nome del driver per la connessione al server jndi da properties
		properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		//inserisco l'url del server jndi
		properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		
		jndiContext = new InitialContext(properties);      

		return jndiContext;
	}


}
