package corso.j2ee.jndi.jboss.services;

import java.io.Serializable;

public class ServizioProva implements Serializable{

	
	
	
	private String campoString;
	private double campoDouble;
	private int campoInt;
	private ServizioDipendenza dipendenza;
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+
		"campoDouble:"+this.getCampoDouble()+" "+
		"campoString:"+this.getCampoString()+" "+
		"campoInt:"+this.getCampoInt()+" "+
		this.getDipendenza().toString();
	}
	public ServizioDipendenza getDipendenza() {
		return dipendenza;
	}
	public void setDipendenza(ServizioDipendenza dipendenza) {
		this.dipendenza = dipendenza;
	}
	public String getCampoString() {
		return campoString;
	}
	public void setCampoString(String campoString) {
		this.campoString = campoString;
	}
	public double getCampoDouble() {
		return campoDouble;
	}
	public void setCampoDouble(double campoDouble) {
		this.campoDouble = campoDouble;
	}
	public int getCampoInt() {
		return campoInt;
	}
	public void setCampoInt(int campoInt) {
		this.campoInt = campoInt;
	}
}
