package apps.stockexchange.beans;

public enum OrderType {

	LIMIT ("Offerta vendita"),
	MARKET ("Proposta acquisto"),
	CANCEL_ORDER ("Annullamento Ordine"),
	UPDATE_ORDER ("Modifica ordine");
		
	private String descrizione;

	private OrderType(String descrizione) {
		this.descrizione = descrizione;
	}
}
