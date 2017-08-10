package apps.stockexchange.beans;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class OrderOutcome implements Serializable{

	private String stockAgentName;
	private String stockAgentId;
	private Date orderDate;
	private String orderId;
	private boolean accepted;
	private String rejectionReason;
	private OrderStatus status;
	
	private static final String EXIT_MESSAGE="CLOSE";
	
	public boolean isExitMessage(){
		return this.getOrderId().equals(EXIT_MESSAGE);
	}
	
}
