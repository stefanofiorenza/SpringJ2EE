package corso.ejb.demo.servizi.basic.io;

import java.util.List;

import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;
import corso.ejb.demo.servizi.basic.io.model.User;

public interface UserService {

	public User echoUser(User utente) throws ServiceException;

	public List<User> getUsers(String criterio) throws ServiceException;
	
	public void addUser (User daAggiungere) throws ServiceException;
}
