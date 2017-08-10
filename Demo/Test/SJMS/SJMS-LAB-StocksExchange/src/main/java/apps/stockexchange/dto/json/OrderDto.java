package apps.stockexchange.dto.json;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDto {

	private String orderId;
	private String orderReferenceId; /** in use for Cancellation or Update order type */
	private String stockName;
	private double price;
	private int quantity;
	private String type;
	private String stockType;
	private String tifType;
		
}
