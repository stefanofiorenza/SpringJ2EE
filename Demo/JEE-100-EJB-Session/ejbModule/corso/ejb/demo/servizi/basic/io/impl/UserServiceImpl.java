package corso.ejb.demo.servizi.basic.io.impl;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateful;


import corso.ejb.demo.servizi.basic.io.UserServiceRemote;
import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;
import corso.ejb.demo.servizi.basic.io.model.User;

@Stateful
public class UserServiceImpl implements UserServiceRemote {

	Vector<User> utenti = new Vector<User>();
	
	public User echoUser(User utente) throws ServiceException {		
		return utente;
	}
	
	public List<User> getUsers(String criterio) throws ServiceException {
		return utenti;
	}

	public void addUser(User daAggiungere) throws ServiceException {
		utenti.add(daAggiungere);
		
	}

}
