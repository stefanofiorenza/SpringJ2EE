package corso.j2ee.jndi.jboss.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.j2ee.jndi.jboss.services.ServizioDipendenza;
import corso.j2ee.jndi.jboss.services.ServizioProva;

public class ScollegaOggettoDaServizioJndi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Context initCtx = getInitialContextForJBoss();
			System.out.println("scollego oggetto collegato a Name 'prova' ...");						
			initCtx.unbind("prova");
			System.out.println("oggetto scollegato.");	
			
			
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
