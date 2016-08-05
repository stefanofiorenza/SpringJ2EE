package app.visa.min.services;

import app.visa.min.beans.Cittadino;
import app.visa.min.services.exceptions.CittadinoException;

public interface CittadinoService {

	
	public void registraNuovoCittadino(Cittadino nuovoCittadino) throws CittadinoException;
}
