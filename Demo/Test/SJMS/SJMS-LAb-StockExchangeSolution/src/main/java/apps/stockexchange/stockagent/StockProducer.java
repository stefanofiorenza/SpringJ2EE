package apps.stockexchange.stockagent;

import apps.stockexchange.beans.Order;

public interface StockProducer {

	boolean sendOrder(Order order);
}
