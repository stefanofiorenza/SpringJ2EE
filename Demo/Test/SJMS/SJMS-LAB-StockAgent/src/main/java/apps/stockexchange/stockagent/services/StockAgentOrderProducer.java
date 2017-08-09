package apps.stockexchange.stockagent.services;

import apps.stockexchange.beans.Order;

public interface StockAgentOrderProducer {

	void sendOrder(Order order);
}
