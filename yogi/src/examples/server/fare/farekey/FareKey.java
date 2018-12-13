package examples.server.fare.farekey;

import yogi.server.base.key.IKey;

import examples.server.carrier.Carrier;
import examples.server.market.Market;

/**
 * @author Vikram Vadavala
 *
 */
public interface FareKey extends IKey {
	int getId();
	Carrier getCarrier();
	Market getMarket();
}
