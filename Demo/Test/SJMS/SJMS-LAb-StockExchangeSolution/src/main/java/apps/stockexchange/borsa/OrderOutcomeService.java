package apps.stockexchange.borsa;

import apps.stockexchange.beans.OrderOutcome;

public interface OrderOutcomeService {

	boolean sendToReplyQueue(OrderOutcome outcome);

}
