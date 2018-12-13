package examples.server.fare.farekey;

import yogi.base.Creator;

import examples.server.carrier.Carrier;
import examples.server.market.Market;
/**
 * @author Vikram Vadavala
 *
 */
public class FareKeyCreator implements Creator<FareKey> {
	
	private int id;
	private Carrier carrier;
	private Market market;
	
	public FareKey create() {
		return new FareKeyImpl(id, carrier, market);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	@Override
	public String toString() {
		return String.valueOf(id)+"/"+carrier+"/"+market;
	}
	
	
}
