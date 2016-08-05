package app.visa.min.services;

import javax.ejb.Remote;

import app.visa.min.beans.Cittadino;
import app.visa.min.services.CittadinoService;

@Remote
public interface CittadinoServiceRemote extends CittadinoService {

	public void registraNuovoCittadino(Cittadino nuovoCittadino);
}
