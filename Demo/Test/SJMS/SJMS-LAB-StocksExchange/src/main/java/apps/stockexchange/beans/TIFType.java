package apps.stockexchange.beans;

/**
 * Time In Force Type:
 * Tempo di validita dell ordine. 
 * 
 * @author s.fiorenza
 *
 */
public enum TIFType {

	/**
	 *identifica un ordine che se non eseguito durante la giornata 
	 * di negoziazione viene automaticamente cancellato alla conclusione della stessa.
	 */
	Day,
	
	/**
	 * good till day: identifica un ordine per il quale è possibile specificare 
	 * una durata superiore al giorno fino ad un massimo di trenta giorni.
	 * 
	 */	
	GTD,
	
	/**
	 *  good till time: identifica un ordine per il quale è possibile specificare, 
	 *  nell’ambito della giornata di trading nella quale è inserito,  
	 *  l’ora, minuto e secondo decorsi i quali l’ordine, se non già eseguito, viene automaticamente cancellato.
	 */
	GTT,
	
	/**
	 * good for auction: identifica un ordine che viene attivato nel momento in cui 
	 * il mercato entra in una fase per la quale è prevista un asta. 
	 * Per il mercato ETFplus equivale esclusivamente all’asta di chiusura.	
	 */
	GFA ,

	/**
	 * at close
	 */
	ATC;
	
	
}
