package apps.stockexchange.beans;


public enum MarketType {

	MTA ("Mercato Telematico Azionario"),
	MOT  ("Mercato Obbligazioni e Titoli Stato"),
	MCW ("Mercato Covered Warrants"),
	EuroMOT ("debiti sovrani Euro"),
	MCP ("Mercato Contratti a Premio"),
	MDA ("Mercato Derivati Azionari"),
	MDTI ("Mercato Derivati Tassi Interessi"),
	TAH ("Trading After Hour");	 
	
	private String descrizione;

	private MarketType(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
