package apps.stockexchange.service;

import apps.stockexchange.beans.Order;
import apps.stockexchange.beans.OrderOutcome;

public interface OrderService {

	OrderOutcome processOrder(Order order);
}
