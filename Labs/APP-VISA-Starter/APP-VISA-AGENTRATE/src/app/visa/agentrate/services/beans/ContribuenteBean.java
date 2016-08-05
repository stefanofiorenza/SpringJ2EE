package app.visa.agentrate.services.beans;

import java.io.Serializable;



public class ContribuenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2588586193000502889L;
	private Long codiceCartaIdentita;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String cittaNascita;
	private String nazioneNascita;
	private String residenzaFiscaleVia;
	private String residenzaFiscaleCitta;
	public Long getCodiceCartaIdentita() {
		return codiceCartaIdentita;
	}
	public void setCodiceCartaIdentita(Long codiceCartaIdentita) {
		this.codiceCartaIdentita = codiceCartaIdentita;
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
	public String getResidenzaFiscaleVia() {
		return residenzaFiscaleVia;
	}
	public void setResidenzaFiscaleVia(String residenzaFiscaleVia) {
		this.residenzaFiscaleVia = residenzaFiscaleVia;
	}
	public String getResidenzaFiscaleCitta() {
		return residenzaFiscaleCitta;
	}
	public void setResidenzaFiscaleCitta(String residenzaFiscaleCitta) {
		this.residenzaFiscaleCitta = residenzaFiscaleCitta;
	}
	@Override
	public String toString() {
		return "ContribuenteBean [cittaNascita=" + cittaNascita
				+ ", codiceCartaIdentita=" + codiceCartaIdentita
				+ ", codiceFiscale=" + codiceFiscale + ", cognome=" + cognome
				+ ", nazioneNascita=" + nazioneNascita + ", nome=" + nome
				+ ", residenzaFiscaleCitta=" + residenzaFiscaleCitta
				+ ", residenzaFiscaleVia=" + residenzaFiscaleVia + "]";
	}
	
	
	
}
