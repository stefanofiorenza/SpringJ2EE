package corso.j2ee.demo.ejb.mail;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Session;

/**
 * Session Bean implementation class DemoMailSessionBean
 */
@Stateless
public class DemoMailSessionBean implements DemoMailSessionBeanLocal,DemoMailSessionBeanRemote {
	
	@Resource(mappedName="Mail")
	Session sessione;


	
    public DemoMailSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void logObjects(){
    	
    	System.out.println("[DemoMailSessionBean]: "+sessione.toString());
    }
    
    
    public void inviaEmai (){
    	
    	System.out.println("[DemoMailSessionBean]: "+sessione.toString()+" invia email");
    	   
    }

}
