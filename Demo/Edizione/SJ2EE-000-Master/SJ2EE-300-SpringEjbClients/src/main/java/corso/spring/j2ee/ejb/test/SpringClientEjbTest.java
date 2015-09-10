package corso.spring.j2ee.ejb.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corso.ejb.demo.servizi.basic.io.model.User;
import corso.spring.j2ee.ejb.service.SpringEjbServiceImpl;

public class SpringClientEjbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext context =new ClassPathXmlApplicationContext("corso/spring/j2ee/ejb/config/application-context.xml");
		SpringEjbServiceImpl servizioSpring =context.getBean("mySpringService",SpringEjbServiceImpl.class);
		User daInserire = new User();
		daInserire.setNome("Stefano");
		daInserire.setCognome("Fiorenza");
		daInserire.setEmail("stefano@email.it");
		daInserire.setTelefono("064534534");
		User risposta= servizioSpring.echoUserDaSpring(daInserire);
		System.out.println(risposta);
	
	}

}
