package examples.server.fare.farekey;

import yogi.base.relationship.RelationshipObjectImpl;

import examples.server.carrier.Carrier;
import examples.server.market.Market;
/**
 * @author Vikram Vadavala
 *
 */

public class FareKeyImpl extends RelationshipObjectImpl<FareKey> implements FareKey {
	
	private int id;
	private Carrier carrier;
	private Market market;
	
	protected FareKeyImpl(int id, Carrier carrier, Market market) {
		super();
		this.id = id;
		this.carrier = carrier;
		this.market = market;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Carrier getCarrier() {
		return carrier;
	}

	@Override
	public Market getMarket() {
		return market;
	}
	
	public String getName() {
		return String.valueOf(id)+"/"+carrier.getName()+"/"+market.getName();
	}

	@Override
	public String toString() {
		return String.valueOf(id)+"/"+carrier+"/"+market;
	}
	
	
}
