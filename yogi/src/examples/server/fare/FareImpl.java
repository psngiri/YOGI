package examples.server.fare;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.action.Action;

import examples.server.fare.farekey.FareKey;
import examples.server.sub.Sub;

public class FareImpl extends RelationshipObjectImpl<Fare> implements Fare {
	
	private FareKey key;
	private Sub version;
	private Action action;
	private int fareAmount;
	
	protected FareImpl(FareKey key, Sub version, Action action, int fareAmount) {
		super();
		this.key = key;
		this.version = version;
		this.action = action;
		this.fareAmount = fareAmount;
	}
	
	public FareKey getKey() {
		return key;
	}

	public Sub getVersion() {
		return version;
	}

	public Action getAction() {
		return action;
	}

	public int getFareAmount() {
		return fareAmount;
	}
	
	@Override
	public String getName() {
		return key.getName()+"/"+version.getName();
	}

	@Override
	public String toString() {
		return key+"/"+version;
	}

}
