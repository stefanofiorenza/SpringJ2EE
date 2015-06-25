package corso.j2ee.jndi.jboss.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import corso.j2ee.jndi.jboss.services.ServizioDipendenza;
import corso.j2ee.jndi.jboss.services.ServizioProva;

public class RegistraServizioJndi {

	private static final String JBOSS_71_PKG_INTERFACES="org.jboss.ejb.client.naming";
	private static final String JBOSS_51_PKG_INTERFACES="org.jnp.interfaces.NamingContextFactory";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//..dopo aver avviato JBoss
		
		try {
			Context initCtx = getInitialContextForJBoss();
			
			ServizioDipendenza dip = new ServizioDipendenza();
			dip.setCampoDipDouble(34.2);
			dip.setCampoDipString("string dipendenza");
			
			ServizioProva prova = new ServizioProva();
			prova.setCampoDouble(12.3);
			prova.setCampoInt(66);
			prova.setCampoString("servizio Prova");
			prova.setDipendenza(dip);
			
			initCtx.bind("prova", prova);
			System.out.println("oggetto "+prova+" registrato correttamente..");
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static Context getInitialContextForJBoss() throws NamingException{
		
//		Properties properties = new Properties();
//		properties.put(Context.URL_PKG_PREFIXES,JBOSS_71_PKG_INTERFACES);
		Context jndiContext = new InitialContext();      

		return jndiContext;
	}
	
}
