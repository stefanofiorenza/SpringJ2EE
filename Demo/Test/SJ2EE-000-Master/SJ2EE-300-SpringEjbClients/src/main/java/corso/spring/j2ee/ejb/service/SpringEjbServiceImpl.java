package corso.spring.j2ee.ejb.service;

import corso.ejb.demo.servizi.basic.io.UserService;
import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;
import corso.ejb.demo.servizi.basic.io.model.User;

public class SpringEjbServiceImpl {

	UserService servizioUserEjb;

	
	public UserService getServizioUserEjb() {
		return servizioUserEjb;
	}

	public void setServizioUserEjb(UserService servizioUserEjb) {
		this.servizioUserEjb = servizioUserEjb;
	}
	
	public User echoUserDaSpring(User daInserire){
		
		User echo = null;
		try {
			echo= servizioUserEjb.echoUser(daInserire);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return echo;
	}
	
}
