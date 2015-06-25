package corso.j2ee.jndi.jboss.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.j2ee.jndi.jboss.services.ServizioDipendenza;
import corso.j2ee.jndi.jboss.services.ServizioProva;

public class AccediServizioRegistrato {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			
			
			System.out.println("Recupero oggetto registrato sul servizio jndi di Jboss...");
			Context initCtx = getInitialContextForJBoss();
			Object p = initCtx.lookup("Mail");
			
			//ServizioProva p = (ServizioProva)initCtx.lookup("java:/Mail");
			System.out.println("Oggetto recuperato:\n");
			System.out.println(p.toString());
			
			
			
		} catch (NamingException e) {
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
