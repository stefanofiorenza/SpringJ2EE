package apps.stockexchange.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order implements Serializable{

	private String stockAgentName;
	private String stockAgentId;
	private String orderId;
	private String orderReferenceId; /** in use for Cancellation or Update order type */
	private String stockName;
	private BigDecimal price;
	private int quantity;
	private OrderType type;
	private StockType stockType;
	private TIFType tifType;
	
	
		
}
