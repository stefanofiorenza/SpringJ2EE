package app.visa.agentrate.services;

import app.visa.agentrate.services.beans.ContribuenteBean;

public class ContribuenteService implements ContribuenteServiceI {

	private static final String PROTOCOL_AGENTRATE="AGE";
	private static int PROTOCOL_NUMBER=0;
	
	
	@Override
	public void registraNuovoContribuente(ContribuenteBean contribuenteBean) {
		PROTOCOL_NUMBER++;
		System.out.println("[Agentrate]: Registrato Contribuente: "+contribuenteBean+ " con No Protocollo: "+PROTOCOL_AGENTRATE+PROTOCOL_NUMBER);
	}

}
