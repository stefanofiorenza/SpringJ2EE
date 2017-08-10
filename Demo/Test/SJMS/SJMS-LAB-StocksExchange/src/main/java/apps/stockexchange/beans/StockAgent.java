package apps.stockexchange.beans;

import lombok.Data;

@Data
public class StockAgent {

	public StockAgent(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private String id;
	private String name;
	
}
