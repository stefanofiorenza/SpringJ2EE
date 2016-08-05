package app.visa.min.beans;

import java.io.Serializable;

public class Cittadino implements Serializable{

	@Override
	public String toString() {
		return "Cittadino [cittaNascita=" + cittaNascita + ", codiceFiscale="
				+ codiceFiscale + ", cognome=" + cognome + ", domicilio="
				+ domicilio + ", idPersona=" + idPersona + ", nazioneNascita="
				+ nazioneNascita + ", nome=" + nome + ", residenza="
				+ residenza + "]";
	}
	private Long idPersona;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String cittaNascita;
	private String nazioneNascita;
	private Indirizzo residenza;
	private Indirizzo domicilio;
	
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCittaNascita() {
		return cittaNascita;
	}
	public void setCittaNascita(String cittaNascita) {
		this.cittaNascita = cittaNascita;
	}
	public String getNazioneNascita() {
		return nazioneNascita;
	}
	public void setNazioneNascita(String nazioneNascita) {
		this.nazioneNascita = nazioneNascita;
	}
	public Indirizzo getResidenza() {
		return residenza;
	}
	public void setResidenza(Indirizzo residenza) {
		this.residenza = residenza;
	}
	public Indirizzo getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Indirizzo domicilio) {
		this.domicilio = domicilio;
	}

}
