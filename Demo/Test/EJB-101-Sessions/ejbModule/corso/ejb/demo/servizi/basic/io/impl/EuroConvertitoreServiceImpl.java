package corso.ejb.demo.servizi.basic.io.impl;

import javax.ejb.Stateless;

import corso.ejb.demo.servizi.basic.io.EuroConvertitoreServiceRemote;
import corso.ejb.demo.servizi.basic.io.exceptions.ServiceException;

@Stateless
public class EuroConvertitoreServiceImpl implements EuroConvertitoreServiceRemote {

	
	public double euroToLire(double euro) throws ServiceException {
		// TODO Auto-generated method stub
		return euro*1936.27;
	}

}
