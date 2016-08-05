package corso.spring.jee.runner;

import app.visa.min.beans.Cittadino;
import app.visa.min.beans.Indirizzo;

public class VisaApplicationClient {
	
	private static int numeroCittadiniMock=100;
	
	
	public static void main(String[] args) {

		
		
	
	}
	
	private static Cittadino creaCittadino(){
		Cittadino result = new Cittadino();
		result.setCittaNascita("Roma");
		result.setCodiceFiscale("DLLAD8233H501E");
		result.setCognome("Biscardi");
		result.setNome("Aldo");
		Indirizzo in = new Indirizzo();
		in.setIndirizzo("Via delle Carrozze");
		in.setCivico("98");
		return result;
	}
	
}
