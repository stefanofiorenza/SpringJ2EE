package apps.stockexchange.stockagent.services;

import apps.stockexchange.beans.OrderOutcome;

public interface OrderOutcomeProcessor {

	void processOrderOutcome(OrderOutcome outcome);

}
