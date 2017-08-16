package apps.stockexchange.borsa.services;

import apps.stockexchange.beans.OrderOutcome;

public interface MessageRouter {

	String routeOrder (OrderOutcome orderOutcome);
}
