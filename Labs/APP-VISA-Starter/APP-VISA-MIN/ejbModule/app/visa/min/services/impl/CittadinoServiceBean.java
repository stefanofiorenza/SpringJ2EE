package app.visa.min.services.impl;

import javax.ejb.Stateless;

import app.visa.min.beans.Cittadino;
import app.visa.min.services.CittadinoServiceLocal;
import app.visa.min.services.CittadinoServiceRemote;
import app.visa.min.services.exceptions.CittadinoException;
import app.visa.min.services.util.ProtocolUtils;

@Stateless
public class CittadinoServiceBean implements CittadinoServiceLocal,
		CittadinoServiceRemote {

	
	
	public void registraNuovoCittadino(Cittadino nuovoCittadino)
			throws CittadinoException {

		String protocollo=ProtocolUtils.nextProtocol();
		System.out.println("[Agentrate]: Registrato cittadino: "+nuovoCittadino.toString()+ " con protocollo "+protocollo);
		
	}

}
