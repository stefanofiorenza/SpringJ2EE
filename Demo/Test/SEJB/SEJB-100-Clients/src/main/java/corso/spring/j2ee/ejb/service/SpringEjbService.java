package corso.spring.j2ee.ejb.service;

import corso.ejb.demo.servizi.basic.io.UserService;
import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;
import corso.ejb.demo.servizi.basic.io.model.User;

public interface SpringEjbService {
	
	public User echoUserDaSpring(User daInserire);
	
}
