package corso.j2ee.jndi.jboss.services;

import java.io.Serializable;

public class ServizioDipendenza implements Serializable{

	private String campoDipString;
	private double campoDipDouble;
	
	
	public String getCampoDipString() {
		return campoDipString;
	}
	public void setCampoDipString(String campoDipString) {
		this.campoDipString = campoDipString;
	}
	public double getCampoDipDouble() {
		return campoDipDouble;
	}
	public void setCampoDipDouble(double campoDipDouble) {
		this.campoDipDouble = campoDipDouble;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  ""+
		"campoDipDouble:"+this.getCampoDipDouble()+" "+
		"campoDipString:"+this.getCampoDipString();
		
	}
	

	
}
